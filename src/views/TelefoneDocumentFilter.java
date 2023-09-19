package views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Filtro de documento personalizado para formatar a entrada de números de telefone.
 * Esta classe estende {@link DocumentFilter} para permitir a inserção e substituição
 * de caracteres de forma que se ajuste ao formato de número de telefone desejado,
 * por exemplo: (XX) XXXXX-XXXX.
 */
public class TelefoneDocumentFilter extends DocumentFilter {

    /**
     * Método que lida com a inserção de texto no documento.
     * Se a string inserida consiste apenas de números, ela é formatada de acordo
     * com o formato de telefone.
     *
     * @param fb      O bypass do filtro, usado para mutações no documento.
     * @param offset  A posição no documento para inserir o texto.
     * @param string  O texto a ser inserido.
     * @param attr    O conjunto de atributos para o texto inserido.
     * @throws BadLocationException Se ocorrer um problema ao acessar o conteúdo do documento.
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("[0-9]+")) {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
            String formattedText = formatTelefone(newText);
            fb.replace(0, fb.getDocument().getLength(), formattedText, attr);
        }
    }

    /**
     * Método que lida com a substituição de texto no documento.
     * Se o texto de substituição consiste apenas de números, ele é formatado de acordo
     * com o formato de telefone.
     *
     * @param fb      O bypass do filtro, usado para mutações no documento.
     * @param offset  A posição inicial no documento para substituir o texto.
     * @param length  O número de caracteres a serem substituídos.
     * @param text    O texto para substituir a seleção atual.
     * @param attrs   O conjunto de atributos para o texto de substituição.
     * @throws BadLocationException Se ocorrer um problema ao acessar o conteúdo do documento.
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("[0-9]+")) {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
            String formattedText = formatTelefone(newText);
            fb.replace(0, fb.getDocument().getLength(), formattedText, attrs);
        }
    }

    /**
     * Formata a entrada para o formato de número de telefone.
     * O texto de entrada é formatado para se parecer com: (XX) XXXXX-XXXX.
     * Qualquer caractere não numérico na entrada é removido.
     *
     * @param input O texto de entrada para ser formatado.
     * @return O texto formatado.
     */
    private String formatTelefone(String input) {
        StringBuilder sb = new StringBuilder(input.replaceAll("[^0-9]", ""));

        if (sb.length() > 2) {
            sb.insert(2, ")");
        }
        if (sb.length() > 0) {
            sb.insert(0, "(");
        }
        if (sb.length() > 9) { // Mudei de 8 para 9
            sb.insert(9, "-"); // Mudei de 8 para 9
        }
        if (sb.length() > 15) { // Mudei de 14 para 15
            sb.delete(15, sb.length()); // Mudei de 14 para 15
        }

        return sb.toString();
    }
}
