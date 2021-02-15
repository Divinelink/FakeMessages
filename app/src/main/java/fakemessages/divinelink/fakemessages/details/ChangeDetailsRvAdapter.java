package fakemessages.divinelink.fakemessages.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fakemessages.divinelink.fakemessages.R;

public class ChangeDetailsRvAdapter extends RecyclerView.Adapter<ChangeDetailsRvAdapter.ChangeDetailsViewHolder> {


    private List<AddressDomain> totalAddresses;
    private OnAddressClickListener listener;
    private Context context;


    public ChangeDetailsRvAdapter(OnAddressClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void setTotalAddresses(List<AddressDomain> totalAddresses) {
        this.totalAddresses = totalAddresses;
    }

    public List<AddressDomain> getTotalAddresses() {
        return totalAddresses;
    }

    static class ChangeDetailsViewHolder extends RecyclerView.ViewHolder {

        final private TextView addressItem;
        final private LinearLayout addressItemRoot;

        public ChangeDetailsViewHolder(View v) {
            super(v);
            this.addressItem = v.findViewById(R.id.addressItemTextView);
            this.addressItemRoot = v.findViewById(R.id.addressItemRoot);
        }
    }

    @NonNull
    @Override
    public ChangeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.address_item, viewGroup, false);
        ChangeDetailsViewHolder vh = new ChangeDetailsViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChangeDetailsViewHolder holder, int position) {
        holder.addressItem.setText(String.format("%s %s",
                totalAddresses.get(position).getAddress(),
                totalAddresses.get(position).getArea()));

        holder.addressItemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddressClick(totalAddresses.get(position), position);
            }
        });

        holder.addressItemRoot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onAddressLongClick(totalAddresses.get(position), position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return totalAddresses.size();
    }
}
