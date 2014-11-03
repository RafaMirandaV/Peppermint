package dreedi.lookaround.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dreedi.lookaround.R;
import dreedi.lookaround.unevento;

public class Inicio extends Fragment {
    public void onClick(View v){
        /*Poner un mensaje con larga duración*/
        Toast.makeText(getActivity(), "Entrando...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), TodoFragment.class);
        startActivity(intent);
    }

    String url = "http://peppermint.dreedi.com/api/get/todos_eventos";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_inicio, container, false);

        TextView txt = (TextView) rootView.findViewById(R.id.textView2);
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i = 0; i < response.length(); i++){
                                JSONObject traer = (JSONObject) response.get(i);

                                String name = traer.getString("name");
                                String place = traer.getString("place");
                                String picture = traer.getString("picture");

                                Log.d("Nombre: ", name);
                                Log.d("lugar: ",place);
                                Log.d("foto: ",picture);


                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }
        );

        queue.add(request);


    return rootView;
    }

}
