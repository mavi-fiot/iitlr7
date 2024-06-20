// package com.example.states;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.logging.Logger;

// import org.fluentd.logger.FluentLogger;
// import org.springframework.stereotype.Component;
// import org.telegram.telegrambots.bots.TelegramLongPollingBot;
// import org.telegram.telegrambots.meta.TelegramBotsApi;
// import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
// import org.telegram.telegrambots.meta.api.objects.Update;
// import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
// import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
// import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
// import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
// import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// @Component
// public class InitialState extends TelegramLongPollingBot {

//     private static final Logger LOGGER = Logger.getLogger(InitialState.class.getName());
//     private static final FluentLogger LOG = FluentLogger.getLogger("telegram.bot", "localhost", 8080);

//     @Override
//     public void onUpdateReceived(Update update) {
//         if (update.hasMessage() && update.getMessage().hasText()) {
//             String messageText = update.getMessage().getText();
//             long chatId = update.getMessage().getChatId();

//             long userId = getCurrentUserId(update);
//             String userName = update.getMessage().getFrom().getUserName();

//             // Logging user data using Fluent Logger
//             Map<String, Object> data = new HashMap<>();
//             data.put("UserID", userId);
//             data.put("UserName", userName);
//             LOG.log("UserData", data);

//             LOGGER.info(String.format("User data logged: UserID=%d, UserName=%s", userId, userName));

//             // Handle commands
//             if (messageText.equals("/start") || messageText.equals("Дізнатись поточні курси")) {
//                 sendCurrencyRates(chatId);
//             } else {
//                 sendDefaultMessage(chatId);
//             }
//         }
//     }

//     @Override
//     public String getBotUsername() {
//         return "valiutobminfast_bot";
//     }

//     @Override
//     public String getBotToken() {
//         return "YOUR_BOT_TOKEN_HERE";
//     }

//     private long getCurrentUserId(Update update) {
//         if (update.hasMessage() && update.getMessage().getFrom() != null) {
//             return update.getMessage().getFrom().getId();
//         }
//         return -1; // або будь-яке інше значення за замовчуванням для некоректного ID
//     }

//     private void sendCurrencyRates(long chatId) {
//         String ratesMessage = "Наведені курси діють з 18:30\n\n" +
//                               "🇺🇦UAH 🇺🇸USD  41.70 / 41.95\n" +
//                               "🇺🇦UAH 🇪🇺EUR  43.00 / 43.45\n" +
//                               "🇺🇦UAH 🇬🇧GBP  51.50 / 52.35\n" +
//                               "🇺🇦UAH 🇨🇭CHF  44.50 / 45.80\n" +
//                               "🇺🇦UAH 🇨🇦CAD  28.60 / 29.25";

//         SendMessage message = new SendMessage();
//         message.setChatId(String.valueOf(chatId));
//         message.setText(ratesMessage);
//         message.setReplyMarkup(getMainMenuKeyboard());

//         try {
//             execute(message);
//         } catch (TelegramApiException e) {
//             LOGGER.severe(String.format("Error sending currency rates: %s", e.getMessage()));
//         }
//     }

//     private void sendDefaultMessage(long chatId) {
//         String defaultMessage = "Виберіть дію з меню нижче:";
//         SendMessage message = new SendMessage();
//         message.setChatId(String.valueOf(chatId));
//         message.setText(defaultMessage);
//         message.setReplyMarkup(getMainMenuKeyboard());

//         try {
//             execute(message);
//         } catch (TelegramApiException e) {
//             LOGGER.severe(String.format("Error sending default message: %s", e.getMessage()));
//         }
//     }

//     private ReplyKeyboardMarkup getMainMenuKeyboard() {
//         ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//         List<KeyboardRow> keyboard = new ArrayList<>();

//         KeyboardRow row = new KeyboardRow();
//         row.add(new KeyboardButton("Дізнатись поточні курси"));

//         keyboard.add(row);
//         keyboardMarkup.setKeyboard(keyboard);
//         keyboardMarkup.setResizeKeyboard(true);
//         keyboardMarkup.setOneTimeKeyboard(false);

//         return keyboardMarkup;
//     }

//     public static void main(String[] args) {
//         try {
//             TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//             botsApi.registerBot(new InitialState());
//             LOGGER.info("Bot started successfully. Press Ctrl+C to terminate.");
//         } catch (TelegramApiException e) {
//             LOGGER.severe(String.format("Error starting bot: %s", e.getMessage()));
//         }
//     }
// }




// package com.example.states;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.logging.Logger;

// import org.fluentd.logger.FluentLogger;
// import org.springframework.stereotype.Component;
// import org.telegram.telegrambots.bots.TelegramLongPollingBot;
// import org.telegram.telegrambots.meta.TelegramBotsApi;
// import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
// import org.telegram.telegrambots.meta.api.objects.Update;
// import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
// import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
// import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
// import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
// import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


// // @State
// @Component
// public class InitialState extends TelegramLongPollingBot {

//     private static final Logger LOGGER = Logger.getLogger(InitialState.class.getName());
//     // private static final String FLUENTD_TAG = "app";
//     // private static final String FLUENTD_HOST = "127.0.0.1";
//     // private static final int FLUENTD_PORT = 24224;

//     // private static final FluentLogger LOG = FluentLogger.getLogger(FLUENTD_TAG, FLUENTD_HOST, FLUENTD_PORT);
//     // private static final FluentLogger LOG = FluentLogger.getLogger("telegram.bot", "127.0.0.1", 24224);
//     private static final FluentLogger LOG = FluentLogger.getLogger("app", "127.0.0.1", 24224);


//     @Override
//     public void onUpdateReceived(Update update) {
//         if (update.hasMessage() && update.getMessage().hasText()) {
//             String messageText = update.getMessage().getText();
//             long chatId = update.getMessage().getChatId();

//             long userId = getCurrentUserId(update);
//             String userName = update.getMessage().getFrom().getUserName();

//             // Logging user data using Fluent Logger
//             Map<String, Object> data = new HashMap<>();
//             data.put("UserID", userId);
//             data.put("UserName", userName);
//             LOG.log("UserData", data);

//             LOGGER.info(String.format("User data logged: UserID=%d, UserName=%s", userId, userName));

//             // Handle commands
//             if (messageText.equals("/start") || messageText.equals("Дізнатись поточні курси")) {
//                 sendCurrencyRates(chatId);
//             } else {
//                 sendDefaultMessage(chatId);
//             }
//         }
//     }

//     @Override
//     public String getBotUsername() {
//         return "valiutobminfast_bot";
//     }

//     @Override
//     public String getBotToken() {
//         return "7405781361:AAE9uXhQHrhC2GjrWVqiK4OZsEnEHP3_IGQ";
//     }

//     private long getCurrentUserId(Update update) {
//         if (update.hasMessage() && update.getMessage().getFrom() != null) {
//             return update.getMessage().getFrom().getId();
//         }
//         return -1; // або будь-яке інше значення за замовчуванням для некоректного ID
//     }

//     private void sendCurrencyRates(long chatId) {
//         String ratesMessage = "Наведені курси діють з 18:30\n\n" +
//                               "🇺🇦UAH 🇺🇸USD  41.70 / 41.95\n" +
//                               "🇺🇦UAH 🇪🇺EUR  43.00 / 43.45\n" +
//                               "🇺🇦UAH 🇬🇧GBP  51.50 / 52.35\n" +
//                               "🇺🇦UAH 🇨🇭CHF  44.50 / 45.80\n" +
//                               "🇺🇦UAH 🇨🇦CAD  28.60 / 29.25";

//         SendMessage message = new SendMessage();
//         message.setChatId(String.valueOf(chatId));
//         message.setText(ratesMessage);
//         message.setReplyMarkup(getMainMenuKeyboard());

//         try {
//             execute(message);
//         } catch (TelegramApiException e) {
//             LOGGER.severe(String.format("Error sending currency rates: %s", e.getMessage()));
//         }
//     }

//     private void sendDefaultMessage(long chatId) {
//         String defaultMessage = "Виберіть дію з меню нижче:";
//         SendMessage message = new SendMessage();
//         message.setChatId(String.valueOf(chatId));
//         message.setText(defaultMessage);
//         message.setReplyMarkup(getMainMenuKeyboard());

//         try {
//             execute(message);
//         } catch (TelegramApiException e) {
//             LOGGER.severe(String.format("Error sending default message: %s", e.getMessage()));
//         }
//     }

//     private ReplyKeyboardMarkup getMainMenuKeyboard() {
//         ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//         List<KeyboardRow> keyboard = new ArrayList<>();

//         KeyboardRow row = new KeyboardRow();
//         row.add(new KeyboardButton("Дізнатись поточні курси"));

//         keyboard.add(row);
//         keyboardMarkup.setKeyboard(keyboard);
//         keyboardMarkup.setResizeKeyboard(true);
//         keyboardMarkup.setOneTimeKeyboard(false);

//         return keyboardMarkup;
//     }

//     public static void main(String[] args) {
//         try {
//             TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//             botsApi.registerBot(new InitialState());
//             LOGGER.info("Bot started successfully. Press Ctrl+C to terminate.");
//         } catch (TelegramApiException e) {
//             LOGGER.severe(String.format("Error starting bot: %s", e.getMessage()));
//         }
//     }



// }

// @State
// @Component
// public class InitialState {
//     private static final FluentLogger LOG = FluentLogger.getLogger("app", "127.0.0.1", 24224);
//     @Message(messgae="*")
//     public void getInfo(Update update) {
//         int userId=ApplicationContext.getCurrentUserId();
//         String username=update.getMessage().getFrom().getUserName();
//         Map<String, Object> data = new HashMap<>();
//         data.put("UserID", userId);
//         data.put("UserName", userName);
//         LOG.log("UserData", data);
//     }
// }

package com.example.states;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.fluentd.logger.FluentLogger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageHandler {
    private static final FluentLogger LOG = FluentLogger.getLogger("app", "127.0.0.1", 24224);
    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());

    public void handleMessage(Update update) {
        LOGGER.info("Received update: " + "update");

        if (update.hasMessage() && update.getMessage().getFrom() != null) {
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();

            LOGGER.info("Processing message from user: " + "userName");


            Map<String, Object> data = new HashMap<>();
            data.put("UserID", userId);
            data.put("UserName", userName);
            data.put("Message", update.getMessage().getText());  // для тестування

            LOG.log("UserData", data);
        }
    }
}
