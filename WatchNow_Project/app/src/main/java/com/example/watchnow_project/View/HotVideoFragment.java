package com.example.watchnow_project.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watchnow_project.Control.DoGetVideo;
import com.example.watchnow_project.Control.HotVideo_Controller;
import com.example.watchnow_project.GetString.Links;
import com.example.watchnow_project.Model.entity.Video;
import com.example.watchnow_project.R;
import com.example.watchnow_project.Tranfer_Interface.I__VideoTranfer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HotVideoFragment extends Fragment{
    ArrayList<Video> videoList;

    RecyclerView rv_Video;

    public static HotVideoFragment newInstance() {
        Bundle args = new Bundle();
        HotVideoFragment fragment = new HotVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.hot_video_fragment,container, false);
        videoList = new ArrayList<>();
        rv_Video = view.findViewById(R.id.rv_Video);
        DoGetVideo doGetVideo = new DoGetVideo(Links.GET_HOT_VIDEO);
        doGetVideo.execute();

        return view;
    }
    public void setRecyclerview(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        HotVideo_Controller adapter = new HotVideo_Controller(getActivity(),videoList);
        rv_Video.setLayoutManager(layoutManager);
        rv_Video.setAdapter(adapter);
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
                //videoList = videos;
                setRecyclerview();
            } catch (Exception json_ex) {
                json_ex.printStackTrace();
            } finally {
            }
        }
    }

}
