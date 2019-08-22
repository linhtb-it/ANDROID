package com.example.watchnow_project.Control;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchnow_project.Model.entity.Category;
import com.example.watchnow_project.Model.entity.Video;
import com.example.watchnow_project.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Category_Controller extends RecyclerView.Adapter<Category_Controller.ViewHolder> {
    ArrayList<Category> categorieList;
    Context context;
    ArrayList<Video> videoList;
    public Category_Controller(ArrayList<Category> categoryList, Context context) {
        this.categorieList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public Category_Controller.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_items, parent, false);
        Category_Controller.ViewHolder viewhoder = new Category_Controller.ViewHolder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Controller.ViewHolder holder, int position) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rv_Category.setLayoutManager(manager);
        Category_Items_Adapter adapter = new Category_Items_Adapter(categorieList.get(position),context,videoList);
        holder.rv_Category.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return categorieList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_Category;
        public ViewHolder(@NonNull View view) {
            super(view);
            rv_Category = view.findViewById(R.id.rv_Category);

        }
        public class DoGetVideo extends AsyncTask<Void, Void, String> {
            private String resultAPI;


            private String url;
            private static final String TAG = "DoGetVideo";

            public DoGetVideo(String url) {
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
                    videoList = new ArrayList<>();
                    JSONArray hotvideoJSonArray = new JSONArray(s);

                    for (int i = 0; i < hotvideoJSonArray.length(); i++) {
                        JSONObject objectHotVideo = hotvideoJSonArray.getJSONObject(i);

                        Video video = new Video();
                        video.setId(objectHotVideo.getString("id"));
                        video.setProvider_ID(objectHotVideo.getString("provider_id"));
                        video.setCategory_ID(objectHotVideo.getString("category_id"));
                        video.setTitle(objectHotVideo.getString("title"));
                        video.setAvatar(objectHotVideo.getString("avatar"));
                        video.setPrice(objectHotVideo.getDouble("price"));
                        video.setStatus(objectHotVideo.getInt("status"));
                        video.setDeleted(objectHotVideo.getInt("deleted"));
                        video.setCopyRight(objectHotVideo.getString("copyright"));
                        video.setArtist_Name(objectHotVideo.getString("artist_name"));
                        video.setAlbum_Name(objectHotVideo.getString("album_name"));
                        video.setFile_Mp4(objectHotVideo.getString("file_mp4"));
                        video.setFile_Mp4_Size(objectHotVideo.getDouble("file_mp4_size"));
                        video.setFile_3gp_Size(objectHotVideo.getDouble("file_3gp_size"));
                        video.setTotal_Downloaded(objectHotVideo.getString("total_downloaded"));
                        video.setDescription(objectHotVideo.getString("description"));
                        video.setDuration(objectHotVideo.getString("duration"));
                        video.setDate_Created(objectHotVideo.getString("date_created"));
                        video.setDate_Modified(objectHotVideo.getString("date_modified"));
                        video.setDate_Published(objectHotVideo.getString("date_published"));
                        video.setUser_Created(objectHotVideo.getString("user_created"));
                        video.setUser_Modified(objectHotVideo.getString("user_modified"));
                        video.setConvert_Status(objectHotVideo.getString("convert_status"));
                        video.setConvert_Time(objectHotVideo.getString("convert_time"));
                        video.setYoutube_Url(objectHotVideo.getString("youtube_url"));
                        video.setTags(objectHotVideo.getString("tags"));
                        video.setDownload_Status(objectHotVideo.getInt("download_status"));
                        video.setFb_Download(objectHotVideo.getString("fb_download"));
                        video.setIcash(objectHotVideo.getString("icash"));
                        video.setFb_Url(objectHotVideo.getString("fb_url"));
                        video.setAws_Status(objectHotVideo.getString("aws_status"));
                        video.setIcash_2(objectHotVideo.getString("icash_2"));
                        video.setPrice_2(objectHotVideo.getDouble("price_2"));
                        video.setPublisher_Category_ID(objectHotVideo.getString("publisher_category_id"));
                        video.setView_Clip_Gold(objectHotVideo.getString("view_clip_gold"));
                        video.setView_Clip_Icash(objectHotVideo.getString("download_clip_icash"));
                        video.setDownload_Clip_Gold(objectHotVideo.getInt("download_clip_gold"));
                        video.setDownload_Clip_Icash(objectHotVideo.getInt("download_clip_icash"));

                        videoList.add(video);
                    }
                } catch (Exception json_ex) {
                    json_ex.printStackTrace();
                } finally {
                }
            }
        }
    }
}
