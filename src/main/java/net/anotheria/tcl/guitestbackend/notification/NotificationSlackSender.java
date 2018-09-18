package net.anotheria.tcl.guitestbackend.notification;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.MethodsClient;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest;
import com.github.seratch.jslack.api.methods.response.chat.ChatPostMessageResponse;
import net.anotheria.tcl.guitestbackend.TestCases;

import java.io.IOException;

public class NotificationSlackSender {
    private static NotificationSlackSender INSTANCE;

    private MethodsClient methodsClient = new Slack().methods();

    public static void main(String... a) {

        new NotificationSlackSender().sendMessage(new CaseResultMessage("764364638463473jhb433", TestCases.REGISTRATION_REGISTRATION, false));
    }

    public void sendMessage(CaseResultMessage resultMessage) {
        try {
            String botToken = "xoxb-35273048835-438002197877-FyfJRaCgbVbEwAFTlUEPIiI1";
            String text = "";
            text += resultMessage.getTestCase().getCaseName() + " " + resultMessage.getStatus() + "\r\n";
            text += "User : " + resultMessage.getUserName() + "\r\n";

            String channel = "#tcl-monitoring-temp";
            ChatPostMessageRequest.ChatPostMessageRequestBuilder builder = ChatPostMessageRequest.builder()
                    .token(botToken)
                    .asUser(false)
                    .text(text)
                    .attachments(null);

            builder.channel(channel);
            ChatPostMessageResponse postMessageResponse = methodsClient.chatPostMessage(builder.build());

            if (!postMessageResponse.isOk()) {
                System.out.println(postMessageResponse.getError());
            }
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String userName, TestCases testCase, boolean success){
        this.sendMessage(new CaseResultMessage(userName, testCase, success));
    }

    public static NotificationSlackSender getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotificationSlackSender();
        }

        return INSTANCE;
    }
}
