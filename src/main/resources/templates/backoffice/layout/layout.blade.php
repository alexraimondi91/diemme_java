<!DOCTYPE html>
<html lang="it">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="csrf-token" content="{{ csrf_token() }}">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>Diemme | Dashboard </title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="{{ URL::asset('backoffice/plugins/fontawesome-free/css/all.min.css') }}">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="{{ URL::asset('backoffice/plugins/overlayScrollbars/css/OverlayScrollbars.min.css') }}">
  <!-- Theme style -->
  <link rel="stylesheet" href="{{ URL::asset('backoffice/dist/css/adminlte.min.css') }}">
  <!-- summernote -->
  <link rel="stylesheet" href="{{ URL::asset('backoffice/plugins/summernote/summernote-bs4.css') }}">
   <!-- iCheck for checkboxes and radio inputs -->
   <link rel="stylesheet" href="{{ URL::asset('backoffice/plugins/icheck-bootstrap/icheck-bootstrap.min.css') }}">
   <!-- Select2 -->
  <link rel="stylesheet" href="{{ URL::asset('backoffice/plugins/select2/css/select2.min.css') }}">
  <link rel="stylesheet" href="{{ URL::asset('backoffice/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css') }}">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

  <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">

  <div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      <!-- Left navbar links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
        </li>
      </ul>

      <!-- SEARCH FORM -->
      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">
        <!-- Messages Dropdown Menu -->
        <!-- Notifications Dropdown Menu -->
        <li class="nav-item dropdown">
          <a class="nav-link" data-toggle="dropdown" href="#">
            <i class="far fa-user"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
            <div class="dropdown-divider"></div>
            <a href="{{ route('logout') }}"
              onclick="event.preventDefault();document.getElementById('logout-form').submit();" class="dropdown-item">
              <i class="fas fa-sign-out-alt mr-2"></i> Logout
            </a>
            <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
              @csrf
            </form>

            <div class="dropdown-divider"></div>
            <a href="{{route('profile')}}" class="dropdown-item">
              <i class="fas fa-users mr-2"></i> Profile
            </a>
          </div>
        </li>
      </ul>
    </nav>
    <!-- /.navbar -->
    <!-- Main Sidebar Container -->
    @include('backoffice/leftbar/leftbar')
    @yield('content')
    <!-- ./wrapper -->
    <!-- Main Footer -->
    <footer class="main-footer">
      <strong>Copyright &copy; 2014-2020 Diemme</strong>
    </footer>
  </div>

  <!-- REQUIRED SCRIPTS -->
  <!-- jQuery -->
  <script src="{{ URL::asset('backoffice/plugins/jquery/jquery.min.js') }}"></script>

  <!-- Bootstrap -->
  <script src="{{ URL::asset('backoffice/plugins/bootstrap/js/bootstrap.bundle.min.js') }}"></script>
  <!-- overlayScrollbars -->
  <script src="{{ URL::asset('backoffice/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js') }}"></script>
  <!-- AdminLTE App -->
  <script src="{{ URL::asset('backoffice/dist/js/adminlte.js') }}"></script>
  <!-- OPTIONAL SCRIPTS -->
  <script src="{{ URL::asset('backoffice/dist/js/demo.js') }}"></script>

  <!-- Summernote -->
  <script src="{{ URL::asset('backoffice/plugins/summernote/summernote-bs4.min.js') }}"></script>
  <script>
    $(function () {
    // Summernote
    $('.textarea').summernote()
  })
  function check(){
  if(document.getElementById('exampleInputFile').files.length)
    $("#fileC").show()
  }
</script>
@yield('js')

</body>

</html>