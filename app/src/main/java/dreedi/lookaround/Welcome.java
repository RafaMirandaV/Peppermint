package dreedi.lookaround;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

import java.util.Arrays;
import java.util.List;


public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Parse.initialize(this, "5UbiUiz2eC3fqycYt5YnJz5oNwmQhAH7fmEonL3W", "yjkWb4beYUHDaNh06WRYhxSb9G150UnnyhueawPs");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            startActivity(new Intent(getApplication(), Evento.class));
        }

        PushService.setDefaultPushCallback(this, Welcome.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public void hola(View v){
        /*Poner un mensaje con larga duración*/
        Toast.makeText(this, "Entrando...", Toast.LENGTH_SHORT).show();

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Logueando con Facebook", "Espere un momento", true);

        List<String> permissions = Arrays.asList("user_about_me",
                "user_relationships", "user_birthday", "user_location", "email");

        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException err) {
                progressDialog.dismiss();


                if (user == null) {
                    Log.d("log", "El usuario cancelo el Loggin");
                } else if (user.isNew()) {

                    Log.d("log", "Primer loggin del Usuario");
                    startActivity(new Intent(getApplication(), Evento.class));

                } else {
                    Log.d("log", "El usuario ya estaba logueado");
                    startActivity(new Intent(getApplication(), Evento.class));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }
}
