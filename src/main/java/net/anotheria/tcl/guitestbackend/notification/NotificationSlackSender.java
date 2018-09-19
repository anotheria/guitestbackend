package net.anotheria.tcl.guitestbackend.notification;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.MethodsClient;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest;
import com.github.seratch.jslack.api.methods.response.chat.ChatPostMessageResponse;
import com.github.seratch.jslack.api.model.Attachment;
import net.anotheria.tcl.guitestbackend.TestCases;
import org.configureme.ConfigurationManager;
import org.configureme.annotations.ConfigureMe;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@ConfigureMe(allfields = true, name = "notification-slack-configuration")
public class NotificationSlackSender {
    private static NotificationSlackSender INSTANCE;

    private MethodsClient methodsClient;

    private String botApiToken;
    private String slackChannel;

    public static void main(String... a) throws MalformedURLException {
        new NotificationSlackSender().sendMessage(new CaseResultMessage("catchall-1536278401658@adoptix.de", TestCases.REGISTRATION_REGISTRATION, false));
    }

    private NotificationSlackSender() {
        this.methodsClient = new Slack().methods();
        ConfigurationManager.INSTANCE.configure(this);
    }

    public void sendMessage(CaseResultMessage resultMessage) {
        try {
            String text = "";
            text += resultMessage.getTestCase().getCaseName() + " " + resultMessage.getStatus() + "\r\n";
            text += "User : " + resultMessage.getUserName() + "\r\n";
            text += "Creation Timestamp : " + resultMessage.getUserName().split("@")[0].split("-")[1];

            List<Attachment> attachments = new LinkedList<>();
            attachments.add(Attachment.builder().title(text).color(resultMessage.isSuccess() ? "good" : "danger").build());
            ChatPostMessageRequest.ChatPostMessageRequestBuilder builder = ChatPostMessageRequest.builder()
                    .token(botApiToken)
                    .asUser(false)
                    //.text(text)
                    .attachments(attachments);

            builder.channel(slackChannel);
            ChatPostMessageResponse postMessageResponse = methodsClient.chatPostMessage(builder.build());

            if (!postMessageResponse.isOk()) {
                System.out.println(postMessageResponse.getError());
            }
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String userName, TestCases testCase, boolean success) {
        this.sendMessage(new CaseResultMessage(userName, testCase, success));
    }

    public static NotificationSlackSender getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotificationSlackSender();
        }

        return INSTANCE;
    }

    public String getBotApiToken() {
        return botApiToken;
    }

    public void setBotApiToken(String botApiToken) {
        this.botApiToken = botApiToken;
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public void setSlackChannel(String slackChannel) {
        this.slackChannel = slackChannel;
    }
}
