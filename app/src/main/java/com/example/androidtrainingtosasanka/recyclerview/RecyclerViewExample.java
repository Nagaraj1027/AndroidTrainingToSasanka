package com.example.androidtrainingtosasanka.recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.model.ListviewModelClass;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerViewExample extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;

    ArrayList<ListviewModelClass> al;
    DataAdapterRecyclerview rv_adapter;
    private Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        findViews();
        createList();

        dragAndDrop();

        initSwipe();
    }

    public void findViews() {
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Recyclerview Example");
*/
        rv = (RecyclerView) findViewById(R.id.rv);
    }


    public void createList() {
        al = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            ListviewModelClass lv_model = new ListviewModelClass();
            lv_model.setAddress("Hyderabad " + i);
            lv_model.setName("Tommy");

            al.add(lv_model);
        }

        rv_adapter = new DataAdapterRecyclerview();
        rv.setAdapter(rv_adapter);


        //rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(RecyclerViewExample.this);
        rv.setLayoutManager(new LinearLayoutManager(RecyclerViewExample.this, LinearLayoutManager.VERTICAL, false));


       /* rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        rv.setLayoutManager(layoutManager);
*/
        rv.setItemAnimator(new DefaultItemAnimator());
    }


    private class DataAdapterRecyclerview extends RecyclerView.Adapter<DataAdapterRecyclerview.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ViewHolder(View view) {
                super(view);
            }
        }

        @Override
        public int getItemCount() {
            return al.size();
        }

        public void removeItem(int position) {
            al.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, al.size());
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_recycler, parent, false);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final MyViewHolder viewholder = (MyViewHolder) holder;

            viewholder.tv_name.setText(al.get(holder.getAdapterPosition()).getAddress());
            viewholder.img.setImageResource(R.drawable.avatarupload);

            viewholder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  Intent to_sampe = new Intent(RecyclerViewExample.this, SampleActivity.class);
                    to_sampe.putExtra("value", al.get(holder.getAdapterPosition()).getAddress());
                    startActivity(to_sampe);
*/

                }
            });


        }

        private class MyViewHolder extends ViewHolder {

            TextView tv_name;
            ImageView img;
            RelativeLayout rl;

            private MyViewHolder(View view) {
                super(view);

                this.tv_name = (TextView) view.findViewById(R.id.tv_name);
                this.img = (ImageView) view.findViewById(R.id.img);
                this.rl = (RelativeLayout) view.findViewById(R.id.rl);

            }
        }
    }

    public void dragAndDrop() {

        final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Collections.swap(al, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                rv_adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                direction = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                al.remove(viewHolder.getAdapterPosition());
                rv_adapter.notifyDataSetChanged();
                if (direction == ItemTouchHelper.LEFT) {
                    al.remove(viewHolder.getAdapterPosition());
                    rv_adapter.notifyDataSetChanged();
                } else {
                    al.remove(viewHolder.getAdapterPosition());
                    rv_adapter.notifyDataSetChanged();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

    public void initSwipe() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Collections.swap(al, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                Log.e("current position ", viewHolder.getAdapterPosition() + "");

                Log.e("target position ", target.getAdapterPosition() + "");
                rv_adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    al.remove(viewHolder.getAdapterPosition());
                    rv_adapter.removeItem(viewHolder.getAdapterPosition());
                    rv_adapter.notifyDataSetChanged();
                } else {
                    rv_adapter.removeItem(viewHolder.getAdapterPosition());
                    rv_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.done);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.avatarupload);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

}
