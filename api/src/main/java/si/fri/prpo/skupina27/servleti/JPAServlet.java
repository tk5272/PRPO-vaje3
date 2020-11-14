package si.fri.prpo.skupina27.servleti;

import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.entitete.Vrata;
import si.fri.prpo.skupina27.storitve.zrna.OsebeZrno;
import si.fri.prpo.skupina27.storitve.zrna.SobeZrno;
import si.fri.prpo.skupina27.storitve.zrna.VrataZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private SobeZrno sobeZrno;

    @Inject
    private VrataZrno vrataZrno;

    @Inject
    private OsebeZrno osebeZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello");
        PrintWriter pw = resp.getWriter();

        pw.println("SOBE:");

        List<Soba> sobe = sobeZrno.getSobe();
        for(int i = 0; i < sobe.size(); i++)
            pw.println(sobe.get(i));

        pw.println("\nOSEBE:");

        List<Oseba> o = osebeZrno.getOsebe();
        for(int i = 0; i < o.size(); i++)
           pw.println(o.get(i));

        pw.println("\nVRATA:");
        List<Vrata> vr = vrataZrno.getAllVrata();
        for(int i = 0; i < vr.size(); i++)
            pw.println(vr.get(i));

        pw.println("\nime sobe z vrati 1: "+ vr.get(0).getSobaId().getImeSobe());
    }
}
