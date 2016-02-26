package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import soap.PontoInteresse;
import soap.ZupWebService;
import soap.ZupWebService_Service;

@ManagedBean
@RequestScoped
public class PontoInteresseBean {

    private PontoInteresse pontoInteresse = new PontoInteresse();
    private List<PontoInteresse> pontos = new ArrayList<PontoInteresse>();
    private List<PontoInteresse> pontosConsulta = new ArrayList<PontoInteresse>();

    private int distancia;
    private int posx;
    private int posy;

    public void salvar() {

        ZupWebService_Service service = new soap.ZupWebService_Service();
        ZupWebService port = service.getZupWebServicePort();

        if (this.pontoInteresse.getId() == 0) {
            port.salvar(this.pontoInteresse);
        } else {
            port.alterar(this.pontoInteresse);
        }

        this.pontoInteresse = new PontoInteresse();

    }

    public void excluir() {

        ZupWebService_Service service = new soap.ZupWebService_Service();
        ZupWebService port = service.getZupWebServicePort();
        
        port.excluir(this.pontoInteresse);

        this.pontoInteresse = new PontoInteresse();

    }

    public void buscarPorDistancia() {

        ZupWebService_Service service = new soap.ZupWebService_Service();
        ZupWebService port = service.getZupWebServicePort();
        
        pontos = port.buscarTodos();

        for (int i = 0; i < pontos.size(); i++) {

            if (calculaPonto(pontos.get(i)) <= this.distancia) {

                this.pontosConsulta.add(pontos.get(i));

            }

        }

    }

    public int calculaPonto(PontoInteresse p) {

        int d;
        int x = p.getPosx();
        int y = p.getPosy();

        d = (int) Math.sqrt((Math.pow((x - posx), 2) + Math.pow((y - posy), 2)));

        return d;
    }

    public List<PontoInteresse> getPontos() {

        ZupWebService_Service service = new soap.ZupWebService_Service();
        ZupWebService port = service.getZupWebServicePort();
        
        pontos = port.buscarTodos();

        return pontos;

    }

    public PontoInteresse getPontoInteresse() {
        return pontoInteresse;
    }

    public void setPontoInteresse(PontoInteresse pontoInteresse) {
        this.pontoInteresse = pontoInteresse;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public List<PontoInteresse> getPontosConsulta() {
        return pontosConsulta;
    }

    public void setPontosConsulta(List<PontoInteresse> pontosConsulta) {
        this.pontosConsulta = pontosConsulta;
    }

}
