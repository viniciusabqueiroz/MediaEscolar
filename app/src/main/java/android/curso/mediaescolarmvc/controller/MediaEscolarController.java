package android.curso.mediaescolarmvc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import android.curso.mediaescolarmvc.datasource.DataSource;
import android.curso.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;
import java.util.List;



public class MediaEscolarController extends DataSource {

    ContentValues dados;

    public MediaEscolarController(Context context) {
        super(context);
    }

    public double calcularMedia(MediaEscolar obj) {
        return (obj.getNotaProva() + obj.getNotaTrabalho()) / 2;
    }

    public String resultadoFinal(double media) {
        return media >= 7 ? "Aprovado" : "Reprovado";
    }

    /**
     * Método que recebe um objeto MediaEscolar e prepara para enviar
     * para o DataSource e salvar no banco de dados.
     *
     */
    public boolean salvar(MediaEscolar obj) {

        boolean sucesso = true;

        dados = new ContentValues();

        dados.put(MediaEscolarDataModel.getMateria(), obj.getMateria());
        dados.put(MediaEscolarDataModel.getBimestre(), obj.getBimestre());
        dados.put(MediaEscolarDataModel.getSituacao(), obj.getSituacao());
        dados.put(MediaEscolarDataModel.getNotaProva(), obj.getNotaProva());
        dados.put(MediaEscolarDataModel.getNotaMateria(), obj.getNotaTrabalho());
        dados.put(MediaEscolarDataModel.getMediaFinal(), obj.getMediaFinal());

        sucesso = insert(MediaEscolarDataModel.getTABELA(), dados);

        return sucesso;
    }

    public boolean deletar(MediaEscolar obj) {

        boolean sucesso = true;

        sucesso = deletar(MediaEscolarDataModel.getTABELA(), obj.getId());

        return sucesso;
    }

    public boolean alterar(MediaEscolar obj) {

        boolean sucesso = true;

        dados = new ContentValues();

        dados.put(MediaEscolarDataModel.getId(), obj.getId());
        dados.put(MediaEscolarDataModel.getMateria(), obj.getMateria());
        dados.put(MediaEscolarDataModel.getBimestre(), obj.getBimestre());
        dados.put(MediaEscolarDataModel.getSituacao(), obj.getSituacao());
        dados.put(MediaEscolarDataModel.getNotaProva(), obj.getNotaProva());
        dados.put(MediaEscolarDataModel.getNotaMateria(), obj.getNotaTrabalho());
        dados.put(MediaEscolarDataModel.getMediaFinal(), obj.getMediaFinal());

        sucesso = alterar(MediaEscolarDataModel.getTABELA(), dados);

        return sucesso;
    }

    public List<MediaEscolar> listar() {
        return getAllMediaEscolar();
    }

    public ArrayList<MediaEscolar> getResultadoFinal() {

        return getAllResultadoFinal();

    }


}
