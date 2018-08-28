package leonardoribeiro.alurafood.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import leonardoribeiro.alurafood.formatter.FormataTelefoneComDdd;

public class validaTelefoneComDdd implements Validador {

    public static final String DEVE_TER_ENTRE_10_OU_11_DIGITOS = "Telefone deve ter entre 10 a 11 digitos";
    private final TextInputLayout textInputTelefoneComDdd;
    private final ValidacaoPadrao validacaoPadrao;
    private final EditText campoTelefoneComDdd;
    private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public validaTelefoneComDdd(TextInputLayout textInputTelefoneComDdd) {
        this.textInputTelefoneComDdd = textInputTelefoneComDdd;
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComDdd);
        this.campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
    }

    private boolean validaEntreDezOuOnzeDigitos(String telefoneComDdd) {
        int digitos = telefoneComDdd.length();
        if (digitos < 10 || digitos > 11) {
            textInputTelefoneComDdd.setError(DEVE_TER_ENTRE_10_OU_11_DIGITOS);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido() {
        if (!validacaoPadrao.estaValido()) return false;
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
        if (!validaEntreDezOuOnzeDigitos(telefoneComDddSemFormatacao)) return false;
        adicionaFormatacao(telefoneComDddSemFormatacao);
        return true;
    }


    private void adicionaFormatacao(String telefoneComDdd) {
        String telefoneComDddFormatado = formatador.formata(telefoneComDdd);
        campoTelefoneComDdd.setText(telefoneComDddFormatado);
    }


}
