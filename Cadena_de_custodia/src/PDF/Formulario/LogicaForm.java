/*-  Javier Agredo 23/04/2018 - javiguittar@gmail.com -*/

package PDF.Formulario;

import Logica.AnalisisLog;
import Logica.CasoLog;
import Logica.EvidenciaLog;

/**
 *
 * @author javier
 */
public class LogicaForm {
private CasoLog casolog;
private EvidenciaLog evidencialog;
private AnalisisLog analisislog;

    public LogicaForm() {
        this.casolog = new CasoLog();
        this.evidencialog = new EvidenciaLog();
        this.analisislog = new AnalisisLog();
    }



    public CasoLog getCasolog() {
        return casolog;
    }

    public void setCasolog(CasoLog casolog) {
        this.casolog = casolog;
    }

    public EvidenciaLog getEvidencialog() {
        return evidencialog;
    }

    public void setEvidencialog(EvidenciaLog evidencialog) {
        this.evidencialog = evidencialog;
    }

    public AnalisisLog getAnalisislog() {
        return analisislog;
    }

    public void setAnalisislog(AnalisisLog analisislog) {
        this.analisislog = analisislog;
    }


}
