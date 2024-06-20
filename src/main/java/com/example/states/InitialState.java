package com.example.states;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class InitialState extends TelegramLongPollingBot {

    private static final Logger LOGGER = Logger.getLogger(InitialState.class.getName());
    private final MessageHandler messageHandler;

    public InitialState(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageHandler.handleMessage(update);  // Викликаємо обробку повідомлення

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Handle commands
            if (messageText.equals("/start") || messageText.equals("Дізнатись поточні курси")) {
                sendCurrencyRates(chatId);
            } else {
                sendDefaultMessage(chatId);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "valiutobminfast_bot";
    }

    @Override
    public String getBotToken() {
        return "7405781361:AAE9uXhQHrhC2GjrWVqiK4OZsEnEHP3_IGQ";
    }

    private void sendCurrencyRates(long chatId) {
        String ratesMessage = "Наведені курси діють з 10:00\n\n" +
                              "🇺🇦UAH 🇺🇸USD  40.70 / 41.05\n" +
                              "🇺🇦UAH 🇪🇺EUR  42.00 / 42.25\n" +
                              "🇺🇦UAH 🇬🇧GBP  50.50 / 51.35\n" +
                              "🇺🇦UAH 🇨🇭CHF  43.50 / 44.80\n" +
                              "🇺🇦UAH 🇨🇦CAD  27.50 / 28.00";

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(ratesMessage);
        message.setReplyMarkup(getMainMenuKeyboard());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.severe(String.format("Error sending currency rates: %s", e.getMessage()));
        }
    }

    private void sendDefaultMessage(long chatId) {
        String defaultMessage = "Виберіть дію з меню нижче:";
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(defaultMessage);
        message.setReplyMarkup(getMainMenuKeyboard());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.severe(String.format("Error sending default message: %s", e.getMessage()));
        }
    }

    private ReplyKeyboardMarkup getMainMenuKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Дізнатись поточні курси"));

        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        return keyboardMarkup;
    }
}
