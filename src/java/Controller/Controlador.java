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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador {

    private CategoriaDao catedao = new CategoriaDaoimpl();
    private ProveedorDao provdao = new ProveedorDaoimpl();
    private ProductoDao proddao = new ProductoDaoimpl();
    
    private Conexion con = new Conexion();
    private JdbcTemplate jdbcTemplate;
    
    // Constructor para inicializar JdbcTemplate
    public Controlador() {
        try {
            this.jdbcTemplate = new JdbcTemplate(con.Conectar());
            System.out.println("JdbcTemplate inicializado correctamente");
        } catch (Exception e) {
            System.err.println("Error inicializando JdbcTemplate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @RequestMapping("index.htm")
    public ModelAndView Ver() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    
    // PRODUCTOS
    @RequestMapping(value = "productosList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaproductoList() {
        ModelAndView mav = new ModelAndView();
        try {
            String sql = "SELECT * FROM producto";
            List datos = this.jdbcTemplate.queryForList(sql);
            mav.addObject("lista", datos);
            mav.setViewName("productosList");
            System.out.println("Productos cargados: " + datos.size());
        } catch (Exception e) {
            System.err.println("Error cargando productos: " + e.getMessage());
            e.printStackTrace();
            mav.addObject("error", "Error cargando productos: " + e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    
    @RequestMapping(value = "agregarProducto.htm")
    public ModelAndView agregarProducto(HttpServletRequest request) {
        try {
            String nomb_prod = request.getParameter("nomb_prod");
            String genero_prod = request.getParameter("genero_prod");
            String talla_prod = request.getParameter("talla_prod");
            String descr_prod = request.getParameter("descr_prod");
            String nom_prov = request.getParameter("nom_prov");
            String nombre_categ = request.getParameter("nombre_categ");

            proddao.agregar(new Producto(0, nomb_prod, genero_prod, talla_prod, descr_prod, nom_prov, nombre_categ));
            System.out.println("Producto agregado correctamente");
        } catch (Exception e) {
            System.err.println("Error agregando producto: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/productosList.htm");
    }
    
    @RequestMapping(value = "deleteProducto.htm")
    public ModelAndView deleteProducto(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            proddao.eliminar(id);
            proddao.reiniciarAutoIncrement(id);
            System.out.println("Producto eliminado correctamente: " + id);
        } catch (Exception e) {
            System.err.println("Error eliminando producto: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/productosList.htm");
    }
    
    @RequestMapping(value = "editarProducto.htm")
    public ModelAndView editarProducto(HttpServletRequest request) {
        try {
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
            System.out.println("Producto editado correctamente: " + id_prod);
        } catch (Exception e) {
            System.err.println("Error editando producto: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/productosList.htm");
    }
    
    // PROVEEDORES
    @RequestMapping(value = "proveedoresList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaproveedorList() {
        ModelAndView mav = new ModelAndView();
        try {
            String sql = "SELECT * FROM proveedor";
            List datos = this.jdbcTemplate.queryForList(sql);
            mav.addObject("lista", datos);
            mav.setViewName("proveedoresList");
            System.out.println("Proveedores cargados: " + datos.size());
        } catch (Exception e) {
            System.err.println("Error cargando proveedores: " + e.getMessage());
            e.printStackTrace();
            mav.addObject("error", "Error cargando proveedores: " + e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    
    @RequestMapping(value = "agregarProveedor.htm")
    public ModelAndView agregarProveedor(HttpServletRequest request) {
        try {
            String nom_prov = request.getParameter("nom_prov");
            String num_cont_prov = request.getParameter("num_cont_prov");
            String fecha_creac_provStr = request.getParameter("fecha_creac_prov");
            String pers_cont_prov = request.getParameter("pers_cont_prov");
            String correo_cont_prov = request.getParameter("correo_cont_prov");
            
            Date fecha_creac_prov = null;
            if (fecha_creac_provStr != null && !fecha_creac_provStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fecha_creac_prov = sdf.parse(fecha_creac_provStr);
                } catch (ParseException e) {
                    System.err.println("Error parseando fecha: " + e.getMessage());
                    fecha_creac_prov = new Date(); // Usar fecha actual como fallback
                }
            } else {
                fecha_creac_prov = new Date(); // Usar fecha actual si no se proporciona
            }

            provdao.agregar(new Proveedor(0, nom_prov, num_cont_prov, fecha_creac_prov, pers_cont_prov, correo_cont_prov));
            System.out.println("Proveedor agregado correctamente");
        } catch (Exception e) {
            System.err.println("Error agregando proveedor: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/proveedoresList.htm");
    }
    
    @RequestMapping(value = "deleteProveedor.htm")
    public ModelAndView deleteProveedor(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            provdao.eliminar(id);
            provdao.reiniciarAutoIncrement(id);
            System.out.println("Proveedor eliminado correctamente: " + id);
        } catch (Exception e) {
            System.err.println("Error eliminando proveedor: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/proveedoresList.htm");
    }
    
    @RequestMapping(value = "editarProveedor.htm")
    public ModelAndView editarProveedor(HttpServletRequest request) {
        try {
            int id_prov = Integer.parseInt(request.getParameter("id_prov"));
            String nom_prov = request.getParameter("nom_prov");
            String num_cont_prov = request.getParameter("num_cont_prov");
            String fecha_creac_provStr = request.getParameter("fecha_creac_prov");
            String pers_cont_prov = request.getParameter("pers_cont_prov");
            String correo_cont_prov = request.getParameter("correo_cont_prov");
            
            Date fecha_creac_prov = null;
            if (fecha_creac_provStr != null && !fecha_creac_provStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fecha_creac_prov = sdf.parse(fecha_creac_provStr);
                } catch (ParseException e) {
                    System.err.println("Error parseando fecha: " + e.getMessage());
                    fecha_creac_prov = new Date();
                }
            } else {
                fecha_creac_prov = new Date();
            }

            Proveedor p = new Proveedor();
            p.setId_prov(id_prov);
            p.setNom_prov(nom_prov);
            p.setNum_cont_prov(num_cont_prov);
            p.setFecha_creac_prov(fecha_creac_prov);  
            p.setPers_cont_prov(pers_cont_prov);
            p.setCorreo_cont_prov(correo_cont_prov);

            provdao.editar(p);
            System.out.println("Proveedor editado correctamente: " + id_prov);
        } catch (Exception e) {
            System.err.println("Error editando proveedor: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/proveedoresList.htm");
    }
    
    // CATEGORÍAS
    @RequestMapping(value = "categoriaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistacategoriaList() {
        ModelAndView mav = new ModelAndView();
        try {
            String sql = "SELECT * FROM categoria";
            List datos = this.jdbcTemplate.queryForList(sql);
            mav.addObject("lista", datos);
            mav.setViewName("categoriaList");
            System.out.println("Categorías cargadas: " + datos.size());
        } catch (Exception e) {
            System.err.println("Error cargando categorías: " + e.getMessage());
            e.printStackTrace();
            mav.addObject("error", "Error cargando categorías: " + e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    
    @RequestMapping(value = "agregarCategoria.htm")
    public ModelAndView agregarCategoria(HttpServletRequest request) {
        try {
            String nombre_categ = request.getParameter("nombre_categ");      
            catedao.agregar(new Categorias(0, nombre_categ));
            System.out.println("Categoría agregada correctamente");
        } catch (Exception e) {
            System.err.println("Error agregando categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    @RequestMapping(value = "deleteCategoria.htm")
    public ModelAndView deleteCategoria(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            catedao.eliminar(id);
            catedao.reiniciarAutoIncrement(id);
            System.out.println("Categoría eliminada correctamente: " + id);
        } catch (Exception e) {
            System.err.println("Error eliminando categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    @RequestMapping(value = "editarCategoria.htm")
    public ModelAndView editarCategoria(HttpServletRequest request) {
        try {
            int id_categ = Integer.parseInt(request.getParameter("id_categ"));
            String nombre_categ = request.getParameter("nombre_categ");      

            Categorias cat = new Categorias();
            cat.setId_categ(id_categ);
            cat.setNombre_categ(nombre_categ);

            catedao.editar(cat);
            System.out.println("Categoría editada correctamente: " + id_categ);
        } catch (Exception e) {
            System.err.println("Error editando categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/categoriaList.htm");
    }
    
    // OTROS LISTADOS
    @RequestMapping(value = "stocksList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistastocksList() {
        ModelAndView mav = new ModelAndView();
        try {
            String sql = "SELECT * FROM stocks";
            List datos = this.jdbcTemplate.queryForList(sql);
            mav.addObject("lista", datos);
            mav.setViewName("stocksList");
        } catch (Exception e) {
            System.err.println("Error cargando stocks: " + e.getMessage());
            mav.addObject("error", "Error cargando stocks: " + e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    
    @RequestMapping(value = "entradaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaentradaList() {
        ModelAndView mav = new ModelAndView();
        try {
            String sql = "SELECT * FROM entrada";
            List datos = this.jdbcTemplate.queryForList(sql);
            mav.addObject("lista", datos);
            mav.setViewName("entradaList");
        } catch (Exception e) {
            System.err.println("Error cargando entradas: " + e.getMessage());
            mav.addObject("error", "Error cargando entradas: " + e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }
    
    @RequestMapping(value = "salidaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistasalidaList() {
        ModelAndView mav = new ModelAndView();
        try {
            String sql = "SELECT * FROM salida";
            List datos = this.jdbcTemplate.queryForList(sql);
            mav.addObject("lista", datos);
            mav.setViewName("salidaList");
        } catch (Exception e) {
            System.err.println("Error cargando salidas: " + e.getMessage());
            mav.addObject("error", "Error cargando salidas: " + e.getMessage());
            mav.setViewName("error");
        }
        return mav;
    }

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView redirectToLogin() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
    
    @RequestMapping(value = "testConexion.htm")
public ModelAndView testConexion() {
    ModelAndView mav = new ModelAndView();
    boolean conexionOk = Conexion.probarConexion();
    mav.addObject("mensaje", conexionOk ? "Conexión exitosa" : "Error de conexión");
    mav.setViewName("test");
    return mav;
}
}