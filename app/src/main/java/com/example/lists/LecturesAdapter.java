package com.example.lists;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LecturesAdapter extends RecyclerView.Adapter<LecturesAdapter.BaseViewHolder> {

    private static final int ITEM_VIEW_TYPE_LECTURE = 0;
    /**
     * Тип элемента списка - неделя
     */
    private static final int ITEM_VIEW_TYPE_WEEK = 1;
    private DisplayMode displayMode = DisplayMode.UNGROUPED;
    private final Resources Resources;
    private List<Object> AdapterItems;
    private List<Lecture> lectures;

    public LecturesAdapter(Resources Resources) {
        this.Resources = Resources;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = AdapterItems.get(position);
        if (item instanceof Lecture){
            return ITEM_VIEW_TYPE_LECTURE;
            }
        else if (item instanceof String) {
            return ITEM_VIEW_TYPE_WEEK;
        }
        else
            throw new RuntimeException("The following item is not supported by adapter: "+ item);
    }


    @NonNull
    @Override
    public LecturesAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case ITEM_VIEW_TYPE_LECTURE: {
                View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lecture, parent, false);
                return new LectureViewHolder(view);
            }
            case ITEM_VIEW_TYPE_WEEK: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_week, parent, false);
                return new WeekHolder(view);
            }
            default:
                throw new IllegalArgumentException("ViewType " + viewType + " is not supported");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Object item = AdapterItems.get(position);
        switch (getItemViewType(position)){
            case ITEM_VIEW_TYPE_LECTURE:
                ((LectureViewHolder) holder).bindViews((Lecture)item);
                break;
            case ITEM_VIEW_TYPE_WEEK:
                ((WeekHolder) holder).bindViews((String) item);
                break;
            default:
                throw new RuntimeException("The following item is not supported by adapter: " + item);
        }
    }


    public void setLectures(List<Lecture> lectures) {
        if (lectures == null) {
            AdapterItems = new ArrayList<>();
            this.lectures = new ArrayList<>();
        } else {
            this.lectures = new ArrayList<>(lectures);
            switch (displayMode){
                case UNGROUPED:
                    AdapterItems = new ArrayList<Object>(lectures);
                    break;
                case GROUP_BY_WEEK:
                    groupLecturesByWeek(lectures);
                    break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return AdapterItems == null ? 0 : AdapterItems.size();
    }

    /**
     * Устанавливает в адаптер режим отображения элементов
     *
     * @param displayMode новый режим отображения
     */
    public void setDisplayMode(@NonNull DisplayMode displayMode) {
        this.displayMode = displayMode;
        setLectures(lectures);
    }

    /**
     * Возвращает позицию лекции в элементах адаптера
     *
     * @param lecture какую лекцию найти
     * @return индекс
     */
    public int getPositionOf(@NonNull Lecture lecture) {
        return AdapterItems.indexOf(lecture);
    }

    private void groupLecturesByWeek(@NonNull List<Lecture> lectures) {
        AdapterItems = new ArrayList<>();
        int weekIndex = -1;
        for (Lecture lecture : lectures) {
            if (lecture.getWeekIndex() > weekIndex) {
                weekIndex = lecture.getWeekIndex();
                AdapterItems.add(Resources.getString(R.string.week_number, weekIndex + 1));
            }
            AdapterItems.add(lecture);
        }
    }

    static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(View itemView) { super(itemView);}
    }

    private static class LectureViewHolder extends BaseViewHolder {

        private TextView number;
        private TextView date;
        private TextView theme;
        private TextView lector;


        public LectureViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            date = itemView.findViewById(R.id.date);
            theme = itemView.findViewById(R.id.theme);
            lector = itemView.findViewById(R.id.lector);
        }

        private void bindViews (Lecture lecture) {
            this.date.setText(lecture.getData());
            this.number.setText(String.valueOf(lecture.getNumber()));
            this.lector.setText(lecture.getLector());
            this.theme.setText(lecture.getTheme());
        }
    }

    private static class WeekHolder extends BaseViewHolder{

        private TextView week;

        WeekHolder(View itemView) {
            super(itemView);
            week = itemView.findViewById(R.id.week);
        }

        private void bindViews(String week){ this.week.setText(week);}
    }
}
