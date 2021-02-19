package fakemessages.divinelink.fakemessages.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fakemessages.divinelink.fakemessages.R;

public class MessageRvAdapter extends RecyclerView.Adapter<MessageRvAdapter.MessageViewHolder> {


    final private List<MessageDomain> totalMessages;

    final private Context context;


    public MessageRvAdapter(List<MessageDomain> totalMessages, Context context) {
//        this.message = message;
        this.totalMessages = totalMessages;
        this.context = context;
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        final private TextView messageTimeItem;
        final private TextView senderMessageItem;
        final private TextView receiverMessageItem;
        final private TextView receiverMessageTime;


        public MessageViewHolder(View v) {
            super(v);
            this.messageTimeItem = v.findViewById(R.id.messageTime);
            this.senderMessageItem = v.findViewById(R.id.senderMessageText);
            this.receiverMessageItem = v.findViewById(R.id.receiverMessageTextView);
            this.receiverMessageTime = v.findViewById(R.id.receiverMessageTime);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.message_item, viewGroup, false);
        MessageViewHolder vh = new MessageViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        holder.messageTimeItem.setText(totalMessages.get(position).getMessageTime());

        String[] username = totalMessages.get(position).getUserName().split(" ");

        holder.senderMessageItem.setText(String.format("%s\n%s\n%s, %s",
                totalMessages.get(position).getMessageCode(),
                totalMessages.get(position).getUserName(),
                totalMessages.get(position).getUserAddress(),
                totalMessages.get(position).getUserCity()));

        holder.receiverMessageItem.setText(String.format("ΜΕΤΑΚΙΝΗΣΗ %s %s\n%s %s, %s",
                totalMessages.get(position).getMessageCode(),
                username[0],
                username[1],
                totalMessages.get(position).getUserAddress(),
                totalMessages.get(position).getUserCity()));

    }

    @Override
    public int getItemCount() {
        return totalMessages.size();
    }
}
