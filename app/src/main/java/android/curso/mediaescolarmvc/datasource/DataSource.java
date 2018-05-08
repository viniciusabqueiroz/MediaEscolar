package android.curso.mediaescolarmvc.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.curso.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import android.curso.mediaescolarmvc.model.MediaEscolar;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcomaddo on 18/01/2018.
 */

public class DataSource extends SQLiteOpenHelper {

    private static final String DB_NAME = "media_escolar.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public DataSource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(MediaEscolarDataModel.criarTabela());

        } catch (Exception e) {

            Log.e("Media", "DB---> ERRO: " + e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues dados) {

        boolean sucesso = true;
        try {
            sucesso = db.insert(tabela, null,
                    dados) > 0;
        } catch (Exception e) {

            sucesso = false;
        }

        return sucesso;
    }

    public boolean deletar(String tabela, int id) {

        boolean sucesso = true;

        sucesso = db.delete(tabela, "id=?",
                new String[]{Integer.toString(id)}) > 0;

        return sucesso;
    }

    public boolean alterar(String tabela, ContentValues dados) {

        boolean sucesso = true;

        int id = dados.getAsInteger("id");

        sucesso = db.update(tabela, dados, "id=?",
                new String[]{Integer.toString(id)}) > 0;

        return sucesso;
    }

    public List<MediaEscolar> getAllMediaEscolar() {

        MediaEscolar obj;

        // TIPADA
        List<MediaEscolar> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + MediaEscolarDataModel.getTABELA() + " ORDER BY materia";

        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {

                obj = new MediaEscolar();

                obj.setId(cursor.getInt(cursor.getColumnIndex(MediaEscolarDataModel.getId())));
                obj.setBimestre(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getBimestre())));
                obj.setMateria(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMateria())));
                obj.setMediaFinal(cursor.getDouble(cursor.getColumnIndex(MediaEscolarDataModel.getMediaFinal())));
                obj.setSituacao(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getSituacao())));
                // TODO: Notas
                obj.setNotaTrabalho(cursor.getDouble(cursor.getColumnIndex(MediaEscolarDataModel.getNotaMateria())));
                obj.setNotaProva(cursor.getDouble(cursor.getColumnIndex(MediaEscolarDataModel.getNotaProva())));


                lista.add(obj);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return lista;
    }

    public ArrayList<MediaEscolar> getAllResultadoFinal() {

        MediaEscolar obj;

        // TIPADA
        ArrayList<MediaEscolar> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + MediaEscolarDataModel.getTABELA() + " ORDER BY materia";

        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {

                obj = new MediaEscolar();

                obj.setId(cursor.getInt(cursor.getColumnIndex(MediaEscolarDataModel.getId())));
                obj.setBimestre(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getBimestre())));
                obj.setMateria(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getMateria())));
                obj.setMediaFinal(cursor.getDouble(cursor.getColumnIndex(MediaEscolarDataModel.getMediaFinal())));
                obj.setSituacao(cursor.getString(cursor.getColumnIndex(MediaEscolarDataModel.getSituacao())));
                obj.setNotaTrabalho(cursor.getDouble(cursor.getColumnIndex(MediaEscolarDataModel.getNotaMateria())));
                obj.setNotaProva(cursor.getDouble(cursor.getColumnIndex(MediaEscolarDataModel.getNotaProva())));


                lista.add(obj);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return lista;
    }

}
