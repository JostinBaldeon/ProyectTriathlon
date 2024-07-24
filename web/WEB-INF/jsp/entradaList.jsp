<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Categorias de Productos</title>

        <!-- Custom fonts for this template -->
        <link href="Resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="Resources/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="Resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.htm">
                    <div class="sidebar-brand-icon ">
                        <img src="Resources/img/logo.png" weight="52px" height="24px" alt="alt"/>
                    </div>
                </a>
                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="index.htm">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="productosList.htm">
                        <i class="fas fa-box"></i>
                        <span>Productos</span></a>
                </li>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="proveedoresList.htm">
                        <i class="fas fa-handshake"></i>
                        <span>Proveedores</span>
                    </a>
                </li>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="stocksList.htm">
                        <i class="fas fa-warehouse"></i>
                        <span>Stock</span>
                    </a>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="categoriaList.htm">
                        <i class="fas fa-tags "></i>
                        <span>Categoria</span>
                    </a>
                </li>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item active">
                    <a class="nav-link collapsed" href="entradaList.htm">
                        <i class="fas fa-arrow-right"></i>
                        <span>Entrada</span>
                    </a>
                </li>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="salidaList.htm">
                        <i class="fas fa-arrow-left"></i>
                        <span>Salida</span>
                    </a>
                </li>


                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Mantenimiento
                </div>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="usuariosList.jsp">
                        <i class="fas fa-users fa-table"></i>
                        <span>Usuarios</span></a>
                </li>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="reporteList.jsp">
                        <i class="fas fa-users fa-table"></i>
                        <span>Reporte</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- end sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                            <li class="nav-item dropdown no-arrow d-sm-none">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                     aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small"
                                                   placeholder="Search for..." aria-label="Search"
                                                   aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Perfil
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <div class="container-fluid">

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header d-sm-flex align-items-center justify-content-between mb-4">
                                <h6 class="m-0 font-weight-bold text-gray-800">Lista de Categorias de Productos</h6>
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalGuardar">
                                    <i
                                        class="fa fa-plus fa-sm text-white-50"></i> Nuevo
                                </button>

                                <!-- Modal para guardar -->
                                <div class="modal fade" id="exampleModalGuardar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel"><i class="fa fa-plus fa-sm text-black-50"></i> Agregar Categoria</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <form>
                                                                <p>
                                                                    <label for="exampleFormControlInput1">Nombre:</label>
                                                                    <input type="text" class="form-control" name="">
                                                                </p>

                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <button type="button" class="btn btn-primary" data-dismiss="modal">Guardar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal para editar -->
                                <div class="modal fade" id="exampleModalEditar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel"><i class="fa fa-plus fa-sm text-black-50"></i> Editar Categoria</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <form id="editForm">
                                                                <p>
                                                                    <label for="nombreInput">Nombre:</label>
                                                                    <input type="text" id="nombreInput" name="nombre" class="form-control">
                                                                </p>
                                                                <input type="hidden" id="idInput" name="id">
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <button type="button" class="btn btn-primary" onclick="guardarEdicion()">Guardar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    function abrirModalEditar(id_categ, nombre_categ) {
                                        document.getElementById('idInput').value = id_categ;
                                        document.getElementById('nombreInput').value = nombre_categ;

                                        $('#exampleModalEditar').modal('show');
                                    }

                                    function guardarEdicion() {
                                        var id_categ = document.getElementById('idInput').value;
                                        var nombre_categ = document.getElementById('nombreInput').value;
                                        alert("ID: " + id_categ); // Mostrar el valor de "id"
                                        alert("Nombre: " + nombre_categ); // Mostrar el valor de "id   
                                        window.location.href = 'CLeditar.htm?id_categ=' + id_categ + '&nombre_categ=' + nombre_categ;
                                    }
                                </script>

                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Nombre</th>
                                                <th>Accion</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="dato" items="${lista}">
                                                <tr>
                                                    <td>${dato.Id_categ}</td>
                                                    <td>${dato.Nombre_categ}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-primary" onclick="abrirModalEditar('${dato.Id_categ}', '${dato.Nombre_categ}')">
                                                            <i class="fa fa-sm text-white-50"></i> Editar
                                                        </button>  
                                                        <a href="CLdelete.htm?id_categ=${dato.Id_categ}" class="btn btn-warning">Eliminar</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->
            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="Resources/vendor/jquery/jquery.min.js"></script>
        <script src="Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="Resources/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="Resources/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="Resources/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="Resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="Resources/js/demo/datatables-demo.js"></script>

    </body>
</html>
