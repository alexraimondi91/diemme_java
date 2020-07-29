@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1>Crea utente</h1>
        </div>
        <div class="col-sm-6">
          <ol class="breadcrumb float-sm-right">
            <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
            <li class="breadcrumb-item active">Crea Utente</li>
          </ol>
        </div>
      </div>
    </div><!-- /.container-fluid -->
  </section>
  <!-- Main content -->
  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <!-- left column -->
        <!-- right column -->
        <div class="col-md-12">
          @if($success ?? '')
          <!-- success allert -->
          <div class="alert alert-success alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento avvenuto con successo.
          </div>
          <!-- /.success allert -->
          @endif
          @error('email')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Email non valida!
          </div>
          <!-- /.success allert -->
          @enderror
          @error('name_user')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Nome non valido!
          </div>
          <!-- /.success allert -->
          @enderror
          @error('surname_user')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Cognome non valido!
          </div>
          <!-- /.success allert -->
          @enderror
          @error('city_user')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Città non valida!
          </div>
          <!-- /.success allert -->
          @enderror
          @error('country_user')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Nazione non valida!
          </div>
          <!-- /.success allert -->
          @enderror
          @error('group')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Gruppo non valido!
          </div>
          <!-- /.success allert -->
          @enderror
          @error('password')
          <!-- success allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto. Password non valida!
          </div>
          <!-- /.success allert -->
          @enderror
          <!-- general form elements disabled -->
          <div class="card card">
            <div class="card-header">
              <h3 class="card-title">Crea Utente</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
              <form method="POST" action="{{route('createUserPersist')}}" role="form">
              @csrf
                <div class="row">
                  <div class="col-sm-4">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Nome </label>
                      <input required name="name_user" type="text" class="form-control" placeholder="Mario">
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Cognome</label>
                      <input required name="surname_user" type="text" class="form-control" placeholder="Rossi">
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-8">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Codice Fiscale </label>
                      <input required name="fiscalCode_user" type="text" class="form-control" placeholder="">
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-4">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Nazione </label>
                      <input required name="country_user" type="text" class="form-control" placeholder="Italia">
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Regione </label>
                      <input required name="region_user" type="text" class="form-control" placeholder="Abruzzo">
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-4">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Città </label>
                      <input required name="city_user" type="text" class="form-control" placeholder="Roma">
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Via </label>
                      <input required name="address_user" type="text" class="form-control" placeholder="Via capo 99">
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-6">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Email </label>
                      <input required name="email" type="email" class="form-control" placeholder="example@example.it">
                    </div>
                  </div>
                  <div class="col-sm-6">
                    <!-- select -->
                    @if($collection ?? '')
                    <div class="form-group">
                      <label>Seleziona gruppo </label>
                      <select name="group" class="form-control">
                        @foreach ($collection as $item)
                        <option value="{{$item->id}}">{{$item->name}}</option>
                        @endforeach
                      </select>
                    </div>
                    @endif
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-6">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Password </label>
                      <input required name="password" type="password" class="form-control" placeholder="Minimo 8 caratteri">
                    </div>
                  </div>
                  <div class="col-sm-6">
                    <!-- text input -->
                    <div class="form-group">
                      <label>Conferma Password </label>
                      <input required name="password_confirmation" type="password" class="form-control" placeholder="Minimo 8 caratteri">
                    </div>
                  </div>
                </div>
            </div>
            <!-- /.card-body -->
            <div class="card-footer">
              <button type="submit" class="btn btn-success float-right">Crea</button>
            </div>
          </form>
            <!-- /.card-footer -->
          </div>
          <!-- /.card -->
          <!-- general form elements disabled -->
          <!-- /.card -->
        </div>
        <!--/.col (right) -->
      </div>
      <!-- /.row -->
    </div><!-- /.container-fluid -->
  </section>
  <!-- /.content -->
</div>
<!-- /.content-wrapper -->
@endsection