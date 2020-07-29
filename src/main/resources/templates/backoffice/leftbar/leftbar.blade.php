<aside class="main-sidebar sidebar-dark-primary elevation-4">
  <!-- Brand Logo -->
  <a href="{{route('index')}}" class="brand-link">
    <img
    src="{{ URL::asset('img/icona.png') }}"
      alt="Diemmelogo" class="brand-image img-circle elevation-3" style="opacity: .8">
    <span class="brand-text font-weight-light">Dashboard Diemme</span>
  </a>

  <!-- Sidebar -->
  <div class="sidebar">
    <!-- Sidebar user panel (optional) -->
    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
      <div class="info">
        <a href="#" class="d-block"> {{ Auth::user()->name_user}} {{ Auth::user()->surname_user}}</a>
      </div>
    </div>

    <!-- Sidebar Menu -->
    <nav class="mt-2">
      <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
        <li class="nav-item has-treeview">
          <a href="{{route('dashboard')}}" class="nav-link">
            <i class="nav-icon fas fa-tachometer-alt"></i>
            <p>Dashboard</p>
          </a>
        </li>
        @foreach ( Auth::user()->serviceHave() as $item)
        @if ($item['id'] == 1 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-user-friends"></i>
            <p>
              Utenti
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
            <a href="{{route('createUser')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Crea utente</p>
              </a>
            </li>
            <li class="nav-item">
            <a href="{{route('manageUser')}}"
                class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione utenti</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 1 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-lock"></i>
            <p>
              Permessi
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
            <a href="{{route('createGroup')}}"
                class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Aggiungi permessi</p>
              </a>
            </li>
            <li class="nav-item">
            <a href="{{route('manageGroup')}}"
                class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione permessi</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 2 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-newspaper"></i>
            <p>
              News
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="{{route('createNews')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Scrivi News</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="{{route('manageNews')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione News</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 3 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-folder-plus"></i>
            <p>
              Prodotti
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="{{route('createProduct')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Aggiungi prodotti</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="{{route('manageProduct')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione prodotti</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 4 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-project-diagram"></i>
            <p>
              Istruzione Preventivi
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="{{route('createQuotation')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Aggiungi istruzione</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="{{route('manageQuotation')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione istruzioni</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 5 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-folder-plus"></i>
            <p>
              Tecnologie
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="{{route('createTechnology')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Aggiungi tecnologie</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="{{route('manageTechnology')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione tecnologie</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 6 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-info"></i>
            <p>
              Contatti
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="{{route('updateContact')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Aggiorna contatti</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 7 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-pencil-ruler"></i>
            <p>
              Design Progetto
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="{{route('createLayout')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Aggiungi layout</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="{{route('manageLayout')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Gestione layout</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 8 ?? '')
        <li class="nav-item has-treeview">
        <a href="{{route('showLayouts')}}" class="nav-link">
            <i class="nav-icon fas fa-image"></i>
            <p>Progetto Layout</p>
          </a>
        </li>
        @endif
        
        @if ($item['id'] == 10 ?? '')
        <li class="nav-item has-treeview">
        <a href="{{route('statusOrder')}}" class="nav-link">
            <i class="nav-icon fas fa-list"></i>
            <p>Status ordini</p>
        </a>
        </li>
        @endif
        @if ($item['id'] == 11 ?? '')
        <li class="nav-item has-treeview">
        <a href="{{route('statusOrderCustomer')}}" class="nav-link">
            <i class="nav-icon fas fa-list"></i>
            <p>Status Ordini</p>
        </a>
        </li>
        @endif
        @if ($item['id'] == 13 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-industry"></i>
            <p>
              Ordini
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
            <a href="{{route('makeList')}}"
                class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Ordini da realizzare</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if ($item['id'] == 16 ?? '')
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-mail-bulk"></i>
            <p>
              Gestione Chat
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
            <a href="{{route('chatIndex')}}"
                class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Visualizza</p>
              </a>
            </li>
          </ul>
        </li>
        @endif
        @if($item['id'] == 9 || $item['id']== 15)
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-envelope"></i>
            <p>
              Inizia chat
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            @if($item['id'] == 15 )
            <li class="nav-item">
              <a href="{{route('chatCreate')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Inizia chat Progetto </p>
              </a>
            </li>
            @endif
            @if($item['id'] == 9 )
            <li class="nav-item">
              <a href="{{route('chatCreateFactory')}}" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Inizia chat Produttore</p>
              </a>
            </li>
            @endif
          </ul>
        </li>
        @endif
        @endforeach
      </ul>

    </nav>
    <!-- /.sidebar-menu -->
  </div>
  <!-- /.sidebar -->
</aside>