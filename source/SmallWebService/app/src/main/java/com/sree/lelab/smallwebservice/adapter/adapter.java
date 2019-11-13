package com.sree.lelab.smallwebservice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sree.lelab.smallwebservice.R;
import com.sree.lelab.smallwebservice.view.UserDetails;
import com.sree.lelab.smallwebservice.models.RetrofitGetResponse;
import java.util.HashMap;
import java.util.List;

//User detail adapter
public class adapter extends RecyclerView.Adapter<adapter.myViewHolder> {
    Context context;
    List<RetrofitGetResponse> responseList;
    HashMap<String, String> UsrDtlHmap ;
    public adapter(Context context, List<RetrofitGetResponse> data) {
        this.context = context;
        this.responseList = data;
    }

    //inflate Item layout in list
    @Override
    public adapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_info_adapter, parent, false);
        return new myViewHolder(view);
    }

    //Bind values to item views
    @Override
    public void onBindViewHolder(adapter.myViewHolder holder,final int position) {
        holder.name.setText(responseList.get(position).getName());
        holder.phNo.setText(responseList.get(position).getuPhoneNo());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uDtl= new Intent(context, UserDetails.class);
                UsrDtlHmap= new HashMap<String, String>();
                addCurrentUsrDetailsToMap(position);
                uDtl.putExtra("hashMap", UsrDtlHmap);
                context.startActivity(uDtl);
            }
        });
    }

    //Total Item count
    @Override
    public int getItemCount() {
        if(responseList != null){
            return responseList.size();
        }
        return 0;

    }
    //view holder class for getting view elements for each item in the list
    public class myViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView name;
        TextView phNo;

        public myViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            name = (TextView) itemView.findViewById(R.id.uName);
            phNo = (TextView) itemView.findViewById(R.id.uPhone);
        }
    }

    //storing user details to hashmap for user detail activity
    private void addCurrentUsrDetailsToMap(int position) {
        UsrDtlHmap.clear();
        UsrDtlHmap.put("id",responseList.get(position).getId());
        UsrDtlHmap.put("name",responseList.get(position).getName());
        UsrDtlHmap.put("username",responseList.get(position).getUserName());
        UsrDtlHmap.put("email",responseList.get(position).getuEmail());
        UsrDtlHmap.put("street",responseList.get(position).getAddressDtl().getStreet());
        UsrDtlHmap.put("suite",responseList.get(position).getAddressDtl().getSuite());
        UsrDtlHmap.put("city",responseList.get(position).getAddressDtl().getCity());
        UsrDtlHmap.put("zipcode",responseList.get(position).getAddressDtl().getZipcode());
        UsrDtlHmap.put("lat",responseList.get(position).getAddressDtl().getGeoDtl().getLat());
        UsrDtlHmap.put("lng",responseList.get(position).getAddressDtl().getGeoDtl().getLng());
        UsrDtlHmap.put("phone",responseList.get(position).getuPhoneNo());
        UsrDtlHmap.put("website",responseList.get(position).getuWebsite());
        UsrDtlHmap.put("cname",responseList.get(position).getCompanyDtl().getCname());
        UsrDtlHmap.put("catchPhrase",responseList.get(position).getCompanyDtl().getcCatchPhrase());
        UsrDtlHmap.put("bs",responseList.get(position).getCompanyDtl().getcBs());
    }

    public void setEmployeeList(List<RetrofitGetResponse> resList) {
        this.responseList = resList;
        notifyDataSetChanged();
    }

}
