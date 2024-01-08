    package com.example.bi.ui.list;

    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;

    import com.example.bi.R;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.PopupMenu;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.paging.PagedList;
    import androidx.recyclerview.widget.ItemTouchHelper;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.bi.R;
    import com.example.data.Course;
    import com.example.bi.adapter.AdapterMain;
    import com.example.bi.adapter.CourseViewHolder;
    import com.example.bi.ui.add.AddGuidanceActivity;
    import com.example.bi.ui.detail.DetailActivity;
    import com.example.bi.util.SortType;
    import com.google.android.material.floatingactionbutton.FloatingActionButton;

    public class ListActivity extends AppCompatActivity {
        private ListViewModel viewModel;
        private RecyclerView rvCourse;
        private final AdapterMain courseAdapter = new AdapterMain(this::onCourseClick);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ListViewModelFactory factory = ListViewModelFactory.createFactory(this);
            viewModel = new ViewModelProvider(this, factory).get(ListViewModel.class);
            Log.d("ListActivity", "ViewModel telah dibuat");
            setFabClick();
            setUpRecycler();
    //        initAction();
            updateList();
            setUpRecycler();
        }

        @Override
        protected void onResume() {
            super.onResume();
            updateList();
        }

        private void setUpRecycler() {
            rvCourse = findViewById(R.id.rv_course);
            rvCourse.setLayoutManager(new LinearLayoutManager(this));
            rvCourse.setAdapter(courseAdapter);
        }

        private void onCourseClick(Course course) {
            //TODO 8 : Intent and show detailed course
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.COURSE_ID, course.getId());
            startActivity(intent);
        }

    //    private void initAction() {
    //        Callback callback = new Callback();
    //        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
    //        itemTouchHelper.attachToRecyclerView(rvCourse);
    //    }
    private void updateList() {
        SortType sortType = SortType.TIME;
        viewModel.getCourses(sortType).observe(this, courses -> {
            if (courses != null) {
                Log.d("ListActivity", "Jumlah data kursus: " + courses.size());
                courseAdapter.submitList(courses);
                findViewById(R.id.tv_empty_list).setVisibility(courses.size() == 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

        private void setFabClick() {
            FloatingActionButton fabAdd = findViewById(R.id.floatingActionButton2);
            fabAdd.setOnClickListener(view -> {
                Intent intent = new Intent(this, AddGuidanceActivity.class);
                startActivity(intent);
            });
        }


            //    private class Callback extends ItemTouchHelper.Callback {
    //        @Override
    //        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    //            return makeMovementFlags(0, ItemTouchHelper.RIGHT);
    //        }
    //
    //        @Override
    //        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
    //            return false;
    //        }
    //
    //    }
    }