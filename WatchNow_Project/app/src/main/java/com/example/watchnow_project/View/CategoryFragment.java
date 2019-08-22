package com.example.watchnow_project.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchnow_project.Control.Category_Controller;
import com.example.watchnow_project.Model.entity.Category;
import com.example.watchnow_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    RecyclerView rvCategory;
    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);

        rvCategory = view.findViewById(R.id.rv_Category);

        return view;
    }

    public class DoGetCategory extends AsyncTask<Void, Void, String> {
        private String resultAPI;


        private String url;
        ArrayList<Category> categoryList;

        private static final String TAG = "DoGetVideo";
        public DoGetCategory(String url) {
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(this.url);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;
                resultAPI = "";
                while ((byteCharacter = is.read()) != -1) {
                    resultAPI += (char) byteCharacter;
                }
                Log.d(TAG, "doInBackground: " + resultAPI);
                return resultAPI;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                categoryList = new ArrayList<>();
                JSONArray categoryJSonArray = new JSONArray(s);

                for (int i = 0; i < categoryJSonArray.length(); i++) {
                    JSONObject objectCategory = categoryJSonArray.getJSONObject(i);
                    Category category = new Category();
                    category.setId(objectCategory.getString("id"));
                    category.setPublisher_ID(objectCategory.getString("publisher_id"));
                    category.setContent_Type(objectCategory.getInt("content_type"));
                    category.setTab(objectCategory.getString("tab"));
                    category.setTitle(objectCategory.getString("title"));
                    category.setAvatar(objectCategory.getString("avatar"));
                    category.setStatus(objectCategory.getInt("status"));
                    category.setDeleted(objectCategory.getInt("deleted"));
                    category.setUser_Created(objectCategory.getString("user_created"));
                    category.setUser_Modified(objectCategory.getString("user_modified"));
                    category.setUser_Modified(objectCategory.getString("date_create"));
                    category.setUser_Modified(objectCategory.getString("date_modified"));
                    category.setParent_ID(objectCategory.getString("parent_id"));
                    category.setLft(objectCategory.getInt("lft"));
                    category.setRgt(objectCategory.getInt("rgt"));
                    category.setLevel(objectCategory.getInt("level"));
                    category.setShort_Code(objectCategory.getString("short_code"));
                    category.setCommand_Code(objectCategory.getString("command_code"));
                    category.setPrice(objectCategory.getDouble("price"));
                    category.setFinished_Message(objectCategory.getString("finished_message"));
                    category.setHelp_Message(objectCategory.getString("help_message"));
                    category.setIcash(objectCategory.getString("icash"));
                    category.setThumb(objectCategory.getString("thumb"));
                    categoryList.add(category);
                }
//                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//                Category_Controller adapter = new Category_Controller(categoryList,getActivity());
//                rvCategory.setLayoutManager(manager);
//                rvCategory.setAdapter(adapter);
            } catch (Exception json_ex) {
                json_ex.printStackTrace();
            } finally {
                //this.cancel(true);
            }
        }
    }
}
