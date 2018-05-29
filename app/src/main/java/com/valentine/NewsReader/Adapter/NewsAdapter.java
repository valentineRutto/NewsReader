package com.valentine.NewsReader.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valentine.NewsReader.Model.Article;
import com.valentine.NewsReader.R;

import java.util.ArrayList;

/**
 * Created by valentine on 19-05-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyviewHolder> {
    private ArrayList<Article> list;
    private Context context;

    public NewsAdapter(ArrayList<Article>list){
        this.list=list;
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        context = parent.getContext();
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {
        holder.title.setText(list.get(position).Title);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=list.get(position).Url;
                WebView wv = new WebView(context);
                wv.loadUrl(url);
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                alert.setView(wv);
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(lp);
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                LinearLayout parent = (LinearLayout) positiveButton.getParent();
                parent.setGravity(Gravity.CENTER_HORIZONTAL);
                View leftSpacer = parent.getChildAt(1);
                leftSpacer.setVisibility(View.GONE);
              }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageButton comment;
        public MyviewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.textview);
            comment=(AppCompatImageButton)itemView.findViewById(R.id.comment);

        }
    }
}
