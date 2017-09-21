package zhangxuelei1506d.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static zhangxuelei1506d.myapplication.R.id.photoView;

/**
 * date 2017/9/19
 * author:张学雷(Administrator)
 * functinn:
 */

public class Fragment_Tupian extends Fragment{

    public String url;
    private ImageView imageView;


    public void setUrl(String url){
        this.url=url;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmengtupian, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageviewtupian);

        Glide.with(this).load(url).into(imageView);






        return view;

    }

}
