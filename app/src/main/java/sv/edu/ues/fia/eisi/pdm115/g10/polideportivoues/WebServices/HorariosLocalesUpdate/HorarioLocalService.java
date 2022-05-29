package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.HorariosLocalesUpdate;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.William.HorariosLocales.HorariosLocales;

public class HorarioLocalService {
    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";
        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);
        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG)
                    .show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }



    public static List<HorariosLocales> obtenerMateriasExterno(String json, Context ctx) {
        List<HorariosLocales> listaHorarios = new ArrayList<HorariosLocales>();
        try {
            JSONArray horarioLocalJSON = new JSONArray(json);
            for (int i = 0; i < horarioLocalJSON.length(); i++) {
                JSONObject obj = horarioLocalJSON.getJSONObject(i);
                HorariosLocales horarioLocal = new HorariosLocales();
                horarioLocal.setIdHorario(obj.getString("IDHORARIO"));
                horarioLocal.setIdLocal(obj.getString("IDLOCAL"));
                horarioLocal.setDisponibilidad(Integer.parseInt(obj.getString("DISPONIBILIDAD")));
                listaHorarios.add(horarioLocal);
            }
            return listaHorarios;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG)
                    .show();
            return null;
        }
    }
}
