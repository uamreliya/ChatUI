package me.jump360.chatui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import me.jump360.chatui.Adapter.EmojiAdapter;
import me.jump360.chatui.Adapter.MessageListAdapter;
import me.jump360.chatui.Model.Message;
import me.jump360.chatui.Model.User;

public class MainActivity extends AppCompatActivity {

    EditText editTextMessage;
    RecyclerView recyclerView;
    MessageListAdapter messageListAdapter;
    List<Message> messageList = new ArrayList<>();
    List<Integer> emojiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMessageData();
        setEmojiList();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle("FANFIGHT");
        toolbar.setSubtitle("Udit, Abhishek, Kushal, Anshul, Harshad, Mayur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        initView();
    }



    private void initView() {

        editTextMessage = findViewById(R.id.et_message);

        editTextMessage.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editTextMessage, InputMethodManager.SHOW_FORCED);


        recyclerView = findViewById(R.id.rv_chat);
        EmojiOnClickListener emojiOnClickListener = new EmojiOnClickListener() {
            @Override
            public void onEmojiClickListener(Message message, View itemView) {
                setUpPopUpView(itemView);
            }
        };
        messageListAdapter = new MessageListAdapter(this, messageList, emojiOnClickListener);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageListAdapter);
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    private void setUpPopUpView(View itemView) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = inflater.inflate(R.layout.popup_layout, null);

        RecyclerView recyclerView = popupView.findViewById(R.id.recyclerView);
        EmojiAdapter emojiAdapter = new EmojiAdapter(emojiList, popupView.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(popupView.getContext(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(emojiAdapter);

        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, true);

        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(itemView.findViewById(R.id.tv_received_message), 20,20, Gravity.TOP);

        popupWindow.showAtLocation(getWindow().getDecorView().findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void setMessageData() {

        messageList.add(new Message("Hello",new User("Abhishek","profileURL"), "11.40"));
        messageList.add(new Message("Hi",new User("Udit","profileURL"), "11:41"));
        messageList.add(new Message("How are you?",new User("Abhishek","profileURL"), "11:41"));
        messageList.add(new Message("I'm good, Thanks.",new User("Udit","profileURL"), "11:42"));
        messageList.add(new Message("How about you?",new User("Udit","profileURL"), "11:42"));
        messageList.add(new Message("I'm good too, Thanks.",new User("Abhishek","profileURL"), "11:43"));
        messageList.add(new Message("Did you hear about this new fantasy sports mobile application ?",new User("Abhishek","profileURL"), "11:43"));
        messageList.add(new Message("There are lots of fantasy application in market, which specifically are you talking about?",new User("Udit","profileURL"), "11:44"));
        messageList.add(new Message("I'm huge fan of Fantasy Sports!",new User("Udit","profileURL"), "11:44"));
        messageList.add(new Message("I'm talking about Fanfight, did u play before ?",new User("Abhishek","profileURL"), "11:45"));
        messageList.add(new Message("No, I never tried this before",new User("Udit","profileURL"), "11:46"));
        messageList.add(new Message("Hey, Abhishek",new User("Kushal","profileURL"), "11:48"));
        messageList.add(new Message("Hi, Kushal",new User("Abhishek","profileURL"), "11:49"));
        messageList.add(new Message("I tried FANFIGHT before and It's really amazing application!!",new User("Kushal","profileURL"), "11:49"));
        messageList.add(new Message("Udit, you should try FANFIGHT, they giving cash prize, I earned 1000 Rupees.",new User("Kushal","profileURL"), "11:49"));
        messageList.add(new Message("Hey Kushal, Do one thing please send me referral I'll download using your link.",new User("Udit","profileURL"), "11:50"));
        messageList.add(new Message("Udit, you should try FANFIGHT, they giving cash prize, I earned 1000 Rupees.",new User("Kushal","profileURL"), "11:49"));
    }

    private void setEmojiList() {

        emojiList.add(R.drawable.ic_smile);
        emojiList.add(R.drawable.ic_confused);
        emojiList.add(R.drawable.ic_angry);
        emojiList.add(R.drawable.ic_crying);
        emojiList.add(R.drawable.ic_kissing);
        emojiList.add(R.drawable.ic_tongue_out);
        emojiList.add(R.drawable.ic_in_love);
        emojiList.add(R.drawable.ic_kissing);
        emojiList.add(R.drawable.ic_tongue_out);
        emojiList.add(R.drawable.ic_in_love);
    }

}
