package Controller;

import Clases.Categorias;
import Clases.Proveedor;
import Config.Conexion;
import Dao.CategoriaDao;
import Dao.ProveedorDao;
import DaoImpl.CategoriaDaoimpl;
import DaoImpl.ProveedorDaoimpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.portlet.ModelAndView; //no sirve
import org.springframework.web.servlet.ModelAndView; //ojooo

@Controller
public class Controlador {

    private CategoriaDao cdao = new CategoriaDaoimpl();
    private ProveedorDao pdao = new ProveedorDaoimpl();
    List datos;
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView();

    @RequestMapping("index.htm")
    public ModelAndView Ver() {
        mav.setViewName("index");
        return mav;
    }
    
    

    //CATEGORI METODOS
    ///LISTAR CATEGORIAS EN CATEGORIA 
    
    ///categoria
    @RequestMapping(value = "categoriaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistacategoriaList() {
        String sql = "select * from categoria";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("categoriaList");
        return mav;
    }
    
    ///productos
    @RequestMapping(value = "productosList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaproductoList() {
        String sql = "select * from producto";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("productosList");
        return mav;
    }
    
    ///proveedor
    @RequestMapping(value = "proveedoresList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaproveedorList() {
        String sql = "select * from proveedor";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("proveedoresList");
        return mav;
    }
    ///CATEGORIA BOTON AGREGAR
    
    ///CATEGORIA BOTON ELIMINAR
    
    ///categoria
    @RequestMapping(value = "CLdeleteCategoria.htm")
    public ModelAndView deleteCategoria(HttpServletRequest request) {
        int id_categ = Integer.parseInt(request.getParameter("id_categ"));
        cdao.eliminar(id_categ);
        return new ModelAndView("redirect:/categoriaList.htm");
    }

    ///proveedor
    @RequestMapping(value = "CLdeleteProveedor.htm")
    public ModelAndView deleteProveedor(HttpServletRequest request) {
        int id_prov = Integer.parseInt(request.getParameter("id_prov"));
        pdao.eliminar(id_prov);
        return new ModelAndView("redirect:/proveedorList.htm");
    }
    ///CATEGORIA BOTON EDITAR
    
    ///categoria
    @RequestMapping(value = "CLeditar.htm")
    public ModelAndView editarCategoria(HttpServletRequest request) {
        int id_categ = Integer.parseInt(request.getParameter("id_categ"));
        String nombre_categ = request.getParameter("nombre_categ");
        Categorias c = new Categorias();
        c.setId_categ(id_categ);
        c.setNombre_categ(nombre_categ);
        cdao.editar(c);
        return new ModelAndView("redirect:/categoriaList.htm");
    }

    ///proveedor
    @RequestMapping(value = "CLeditar.htm")
    public ModelAndView editarProveedor(HttpServletRequest request) {
        int id_prov = Integer.parseInt(request.getParameter("id_prov"));
        String nom_prov = request.getParameter("nom_prov");
        String num_cont_prov = request.getParameter("num_cont_prov");
        String fecha_creac_prov = request.getParameter("fecha_creac_prov");
        String pers_cont_prov = request.getParameter("pers_cont_prov");
        String correo_cont_prov = request.getParameter("correo_cont_prov");
        Proveedor p = new Proveedor();
        p.setId_prov(id_prov);
        p.setNom_prov(nom_prov);
        pdao.editar(p);
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView redirectToLogin() {
        mav.setViewName("login");
        return mav;
    }


}
