package com.lzy.hello;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lzy.
 */

public class GoodsSpecTypeNumberAdapter extends BaseAdapter<StoreManagerListEntity.SkuListEntity, GoodsSpecTypeNumberAdapter.GoodsSpecTypeViewHolder> {


    public GoodsSpecTypeNumberAdapter(Context context, List<StoreManagerListEntity.SkuListEntity> list) {
        super(context, list);
    }

    @Override
    public GoodsSpecTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsSpecTypeViewHolder(mInflater.inflate(R.layout.item_goods_spec_number, parent, false));
    }

    @Override
    public void onBindViewHolder(GoodsSpecTypeViewHolder holder, int position) {
        StoreManagerListEntity.SkuListEntity item = getItem(position);


        holder.mEt_number_price.setTag(position * 100 + 1);
        holder.mEt_number_content.setTag(position * 100 + 2);

        holder.mTv_number_name.setText(item.spec);
        if (!TextUtils.isEmpty(item.stock)) {
            holder.mEt_number_content.setText(item.stock);
        }
        if (!TextUtils.isEmpty(item.price)) {
            holder.mEt_number_price.setText(item.price);
        }

        holder.mEt_number_price.addTextChangedListener(new TextSwitcher(holder, "1"));
        holder.mEt_number_content.addTextChangedListener(new TextSwitcher(holder, "2"));
    }

    class GoodsSpecTypeViewHolder extends RecyclerView.ViewHolder {


        private final TextView mTv_number_name;
        private final EditText mEt_number_price;
        private final EditText mEt_number_content;

        GoodsSpecTypeViewHolder(View itemView) {
            super(itemView);
            mTv_number_name = itemView.findViewById(R.id.tv_number_name);
            mEt_number_price = itemView.findViewById(R.id.et_number_price);
            mEt_number_content = itemView.findViewById(R.id.et_number_content);
        }
    }

    class TextSwitcher implements TextWatcher {
        private GoodsSpecTypeViewHolder mHolder;
        private String mType;

        TextSwitcher(GoodsSpecTypeViewHolder mHolder, String type) {
            this.mHolder = mHolder;
            mType = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if ("1".equals(mType)) {
                int position = (Integer) mHolder.mEt_number_price.getTag();
                ((GoodsSpecActicity) mContext).saveEditData(position, mType, s.toString());
            } else if ("2".equals(mType)) {
                int position = (Integer) mHolder.mEt_number_content.getTag();
                ((GoodsSpecActicity) mContext).saveEditData(position, mType, s.toString());
            }
        }
    }
}
