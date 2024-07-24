package Controller;

import Clases.Categorias;
import Clases.Producto;
import Clases.Proveedor;
import Config.Conexion;
import Dao.CategoriaDao;
import Dao.ProductoDao;
import Dao.ProveedorDao;
import DaoImpl.CategoriaDaoimpl;
import DaoImpl.ProductoDaoimpl;
import DaoImpl.ProveedorDaoimpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private CategoriaDao catedao = new CategoriaDaoimpl();
    private ProveedorDao provdao = new ProveedorDaoimpl();
    private ProductoDao proddao = new ProductoDaoimpl();
    int id;
    List datos;
    
    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView();

    @RequestMapping("index.htm")
    public ModelAndView Ver() {
        mav.setViewName("index");
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
    
    // productos - Botón Agregar
    @RequestMapping(value = "agregarProducto.htm")
    public ModelAndView agregarProducto(HttpServletRequest request) {
        String nomb_prod = request.getParameter("nomb_prod");
        String genero_prod = request.getParameter("genero_prod");
        String talla_prod = request.getParameter("talla_prod");
        String descr_prod = request.getParameter("descr_prod");
        String nom_prov = request.getParameter("nom_prov");
        String nombre_categ = request.getParameter("nombre_categ");

        proddao.agregar(new Producto(0, nomb_prod, genero_prod, talla_prod, descr_prod, nom_prov, nombre_categ));
        return new ModelAndView("redirect:/productosList.htm");
    }
    
    // productos - Botón Eliminar
    @RequestMapping(value = "deleteProducto.htm")
    public ModelAndView deleteProducto(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        proddao.eliminar(id);
        proddao.reiniciarAutoIncrement(id);
        return new ModelAndView("redirect:/productosList.htm");
    }
    
    // productos - Botón Editar
    @RequestMapping(value = "editarProducto.htm")
    public ModelAndView editarProducto(HttpServletRequest request) {
        int id_prod = Integer.parseInt(request.getParameter("id_prod"));
        String nomb_prod = request.getParameter("nomb_prod");
        String genero_prod = request.getParameter("genero_prod");
        String talla_prod = request.getParameter("talla_prod");
        String descr_prod = request.getParameter("descr_prod");
        String nom_prov = request.getParameter("nom_prov");
        String nombre_categ = request.getParameter("nombre_categ");

        Producto prod = new Producto();
        prod.setId_prod(id_prod);
        prod.setNomb_prod(nomb_prod);
        prod.setGenero_prod(genero_prod);
        prod.setTalla_prod(talla_prod);  
        prod.setDescr_prod(descr_prod);
        prod.setNom_prov(nom_prov);
        prod.setNombre_categ(nombre_categ);

        proddao.editar(prod);
        return new ModelAndView("redirect:/productosList.htm");
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
    
    // Proveedor - Botón Agregar
    @RequestMapping(value = "agregarProveedor.htm")
    public ModelAndView agregarProveedor(HttpServletRequest request) {
        String nom_prov = request.getParameter("nom_prov");
        String num_cont_prov = request.getParameter("num_cont_prov");
        String fecha_creac_provStr = request.getParameter("fecha_creac_prov");
        String pers_cont_prov = request.getParameter("pers_cont_prov");
        String correo_cont_prov = request.getParameter("correo_cont_prov");
        
        Date fecha_creac_prov = null;
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Usa el formato que corresponda a tus datos
        fecha_creac_prov = sdf.parse(fecha_creac_provStr);
    } catch (ParseException e) {
        e.printStackTrace();
        // Maneja el error de conversión de fecha aquí
    }

        provdao.agregar(new Proveedor(0,nom_prov,num_cont_prov,fecha_creac_prov,pers_cont_prov,correo_cont_prov)); 
        return new ModelAndView("redirect:/proveedoresList.htm");
    }
    
    // Proveedor - Botón Eliminar
    @RequestMapping(value = "deleteProveedor.htm")
    public ModelAndView deleteProveedor(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        provdao.eliminar(id);
        provdao.reiniciarAutoIncrement(id);
        return new ModelAndView("redirect:/proveedoresList.htm");
    }
    
    // Proveedor - Botón Editar
    @RequestMapping(value = "editarProveedor.htm")
    public ModelAndView editarProveedor(HttpServletRequest request) {
        int id_prov = Integer.parseInt(request.getParameter("id_prov"));
        String nom_prov = request.getParameter("nom_prov");
        String num_cont_prov = request.getParameter("num_cont_prov");
        String fecha_creac_provStr = request.getParameter("fecha_creac_prov");
        String pers_cont_prov = request.getParameter("pers_cont_prov");
        String correo_cont_prov = request.getParameter("correo_cont_prov");
        
        Date fecha_creac_prov = null;
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Usa el formato que corresponda a tus datos
        fecha_creac_prov = sdf.parse(fecha_creac_provStr);
    } catch (ParseException e) {
        e.printStackTrace();
        // Maneja el error de conversión de fecha aquí
    }

        Proveedor p = new Proveedor();
        p.setId_prov(id_prov);
        p.setNom_prov(nom_prov);
        p.setNum_cont_prov(num_cont_prov);
        p.setFecha_creac_prov(fecha_creac_prov);  
        p.setPers_cont_prov(pers_cont_prov);
        p.setCorreo_cont_prov(correo_cont_prov);

        provdao.editar(p);
        return new ModelAndView("redirect:/proveedoresList.htm");
    }
    
    ///stock
    @RequestMapping(value = "stocksList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistastocksList() {
        String sql = "select * from stocks";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("stocksList");
        return mav;
    }
    
    ///categoria
    @RequestMapping(value = "categoriaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistacategoriaList() {
        String sql = "select * from categoria";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("categoriaList");
        return mav;
    }
    
    // Categoria - Botón Agregar
    @RequestMapping(value = "agregarCategoria.htm")
    public ModelAndView agregarCategoria(HttpServletRequest request) {
        String nombre_categ = request.getParameter("nombre_categ");      

        catedao.agregar(new Categorias(0,nombre_categ)); 
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    // Categoria - Botón Eliminar
    @RequestMapping(value = "deleteCategoria.htm")
    public ModelAndView deleteCategoria(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        catedao.eliminar(id);
        catedao.reiniciarAutoIncrement(id);
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    // Categoria - Botón Editar
    @RequestMapping(value = "editarCategoria.htm")
    public ModelAndView editarCategoria(HttpServletRequest request) {
        int id_prov = Integer.parseInt(request.getParameter("id_prov"));
        String nom_prov = request.getParameter("nom_prov");      

        Proveedor p = new Proveedor();
        p.setId_prov(id_prov);
        p.setNom_prov(nom_prov);

        provdao.editar(p);
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    ///entrada
    @RequestMapping(value = "entradaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaentradaList() {
        String sql = "select * from entrada";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("entradaList");
        return mav;
    }
    
    ///salida
    @RequestMapping(value = "salidaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistasalidaList() {
        String sql = "select * from salida";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("salidaList");
        return mav;
    }
    
    
    
    
    
    

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView redirectToLogin() {
        mav.setViewName("login");
        return mav;
    }


}
