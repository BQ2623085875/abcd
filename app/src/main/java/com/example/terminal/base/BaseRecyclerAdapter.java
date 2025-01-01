package com.example.terminal.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.listener.OnTextClickListener;
import com.example.terminal.util.CommonUtils;

import java.util.List;

/**
 * @author : YanKing
 * @date : 2019/6/12  16:32
 * @Description :
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mList;
    protected Context mContext;
    protected OnItemClickListener listener ;
    protected boolean IsEditor ;

    public BaseRecyclerAdapter(Context mContext, List<T> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(setContentView() , viewGroup, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        bindData(holder , mList.get(position) , position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 初始化Adapter的布局
     *
     * @return 返回布局文件, 例如:R.layout.item
     */
    public abstract int setContentView();

    /**
     * 设置数据
     * @param holder
     * @param data
     * @param position
     */
    protected abstract void bindData(BaseViewHolder holder, T data, int position);

    /**
     * 刷新填充器
     * @param mList
     */
    public void updateAdapter(List<T> mList){
        this.mList = mList ;
        notifyDataSetChanged();
    }

    /**
     * 刷新填充器
     * @param mList
     */
    public void updateAdapter(List<T> mList , boolean isEditor){
        this.mList = mList ;
        this.IsEditor = isEditor ;
        notifyDataSetChanged();
    }

    /**
     * 给条目设置点击监听
     * @param view
     * @param position
     */
    protected void setItemClickListener(View view , final int position){
        if(view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CommonUtils.isFastDoubleClick())
                        return;

                    if (listener != null)
                        listener.onClick(v, position);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener != null)
                        listener.onLongClick(v , position);
                    return true;
                }
            });
        }
    }

    /**
     * 给条目设置点击监听
     * @param view
     * @param position
     */
    protected void setOnItemClickListener(View view , final int position){
        if(view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CommonUtils.isFastDoubleClick())
                        return;

                    if (listener != null)
                        listener.onClick(v, position);
                }
            });
        }
    }

    /**
     * 给条目设置点击监听
     * @param view
     * @param position
     */
    protected void setOnTextClickListener(View view , final int position){
        if(view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CommonUtils.isFastDoubleClick())
                        return;

                    if (onTextClickListener != null)
                        onTextClickListener.onClick(v, position);
                }
            });
        }
    }

    /**
     * 给条目设置点击监听
     * @param view
     * @param position
     */
    protected void setOnItemLongClickListener(View view , final int position){
        if(view != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener != null)
                        listener.onLongClick(v , position);
                    return true;
                }
            });
        }
    }

    /**
     * 设置条目点击事件
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener ;
    }

    /**
     * 条目字段点击事件
     */
    private OnTextClickListener onTextClickListener ;

    public void setOnTextClickListener(OnTextClickListener listener){
        this.onTextClickListener = listener ;
    }
}
