@extends('backoffice/layout/layout')
@section('content')
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1>Profilo</h1>
        </div>
        <div class="col-sm-6">
          <ol class="breadcrumb float-sm-right">
            <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
            <li class="breadcrumb-item active">Profilo</li>
          </ol>
        </div>
      </div>
    </div><!-- /.container-fluid -->
  </section>

  <!-- Main content -->
  <section class="content">
    <div class="container-fluid">
      @if($warning ?? '')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Aggiornamento non avvenuto!
      </div>
      <!-- warning allert -->
      @endif
      @error('email')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Mail non corretta!
      </div>
      <!-- warning allert -->
      @enderror
      @error('password')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Password non corretta!
      </div>
      <!-- warning allert -->
      @enderror
      @error('password_old')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Password vecchia non inserita!
      </div>
      <!-- warning allert -->
      @enderror
      @error('newpassword')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Nuova password non corretta!
      </div>
      <!-- warning allert -->
      @enderror
      @error('name_user')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Nome non inserito
      </div>
      <!-- warning allert -->
      @enderror
      @error('surname_user')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Cognome non inserito
      </div>
      <!-- warning allert -->
      @enderror
      @error('address_user')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Via non inserita
      </div>
      <!-- warning allert -->
      @enderror
      @error('country_user')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Nazione non inserita
      </div>
      <!-- warning allert -->
      @enderror
      @error('region_user')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Regione non inserita
      </div>
      <!-- warning allert -->
      @enderror
      @error('city_user')
      <!-- warning allert -->
      <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-exclamation"></i></h4>
        Cità non inserita
      </div>
      <!-- warning allert -->
      @enderror
      <div class="row">

        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="card card-primary card-outline">
            <div class="card-body box-profile">
              <h3 class="profile-username text-center">{{ Auth::user()->name_user}} {{ Auth::user()->surname_user}}
              </h3>
              @if('collection' ?? '')

              <p class="text-muted text-center">{{ $collection->name}}</p>

              @endif
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->

          <!-- About Me Box -->
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Info personali</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
              <strong><i class="fas fa-map-marker-alt mr-1"></i> Via</strong>

              <p class="text-muted">
                {{ Auth::user()->address_user}}
              </p>

              <hr>

              <strong><i class="fas fa-map mr-1"></i> Nazione</strong>

              <p class="text-muted">{{Auth::user()->country_user}}</p>

              <hr>

              <strong><i class="fas fa-map mr-1"></i> Regione</strong>

              <p class="text-muted">{{Auth::user()->region_user}}</p>

              <hr>

              <strong><i class="fas fa-city mr-1"></i> Città</strong>

              <p class="text-muted">{{Auth::user()->city_user  }}</p>

              <hr>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        <!-- /.col -->
        <div class="col-md-9">
          <div class="card">
            <div class="card-header p-2">
              <ul class="nav nav-pills">
                <li class="nav-item"><a class="nav-link active" href="#activity" data-toggle="tab">Modifica password</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#activityusername" data-toggle="tab">Modifica email</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#settings" data-toggle="tab">Modifica info personali</a>
                </li>
              </ul>
            </div><!-- /.card-header -->
            <div class="card-body">
              <div class="tab-content">
                <div class="active tab-pane" id="activity">
                  <form class="form-horizontal" method="POST" action="{{route('updateCredentialPasswordPersist')}}">
                    @csrf
                    <div class="form-group row">
                      <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                        <input required type="email" name="email" required placeholder="Email" class="form-control"
                          id="inputEmail">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputPassword" class="col-sm-2 col-form-label">Password vecchia</label>
                      <div class="col-sm-10">
                        <input required type="password" required id="password" name="password_old" class="form-control"
                          id="inputPassword">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputPassword" class="col-sm-2 col-form-label">Nuova Password</label>
                      <div class="col-sm-10">
                        <input required type="password" required id="password" name="password" class="form-control"
                          id="inputPassword" placeholder="Min 8 caratteri">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputSkills" class="col-sm-2 col-form-label">Confirm Password</label>
                      <div class="col-sm-10">
                        <input required id="password-confirm" required type="password" name="password_confirmation"
                          class="form-control" id="inputSkills">
                      </div>
                    </div>
                    <div class="form-group row">
                      <div class="offset-sm-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Salva</button>
                      </div>
                    </div>
                  </form>
                </div>
                <!-- /.tab-pane -->
                <div class="tab-pane" id="activityusername">
                  <form class="form-horizontal" method="POST" action="{{route('updateCredentialEmailPersist')}}">
                    @csrf
                    <div class="form-group row">
                      <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                        <input required type="email" name="email" required class="form-control" id="inputEmail"
                          placeholder="Email">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputEmail" class="col-sm-2 col-form-label">Nuova email</label>
                      <div class="col-sm-10">
                        <input required type="email" required id="email" name="newemail" class="form-control"
                          id="inputEmail">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                      <div class="col-sm-10">
                        <input required type="password" required id="password " name="password" class="form-control"
                          id="inputPassword">
                      </div>
                    </div>
                    <div class="form-group row">
                      <div class="offset-sm-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Salva</button>
                      </div>
                    </div>
                  </form>
                </div>
                <!-- /.tab-pane -->
                <div class="tab-pane" id="settings">
                  <form class="form-horizontal" method="POST" action="{{route('updateProfilePersist')}}">
                    @csrf
                    <div class="form-group row">
                      <label for="inputName" class="col-sm-2 col-form-label">Nome</label>
                      <div class="col-sm-10">
                        <input required type="text" maxlength="60" name="name_user" class="form-control" id="inputName"
                          value="{{Auth::user()->name_user}}">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputName" class="col-sm-2 col-form-label">Cognome</label>
                      <div class="col-sm-10">
                        <input required type="text" maxlength="60" name="surname_user" class="form-control"
                          id="inputName" value="{{Auth::user()->surname_user}}">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputName2" class="col-sm-2 col-form-label">Via</label>
                      <div class="col-sm-10">
                        <input required type="text" maxlength="100" name="address_user" class="form-control"
                          id="inputName2" value="{{Auth::user()->address_user }}">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputExperience" class="col-sm-2 col-form-label">Nazione</label>
                      <div class="col-sm-10">
                        <input required type="text" maxlength="60" name="country_user" class="form-control"
                          id="inputName2" value="{{Auth::user()->country_user}}">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputExperience" class="col-sm-2 col-form-label">Regione</label>
                      <div class="col-sm-10">
                        <input required type="text" maxlength="60" name="region_user" class="form-control"
                          id="inputName2" value="{{Auth::user()->region_user}}">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputExperience" class="col-sm-2 col-form-label">Cità</label>
                      <div class="col-sm-10">
                        <input type="text" maxlength="60" name="city_user" class="form-control" id="inputName2"
                          value="{{Auth::user()->city_user}}">
                      </div>
                    </div>
                    @foreach (Auth::user()->serviceHave() as $item)
                    @if($item['id'] == 7)
                      <div class="form-group row">
                        <label for="inputExperience" class="col-sm-2 col-form-label">Stato</label>
                        <div class="icheck-primary d-inline">
                          &NonBreakingSpace;<input type="checkbox" name="active" id="checkboxPrimary1" @if(Auth::user()->active) checked @endif>
                          <label for="checkboxPrimary1">Attivo </label>
                        </div>
                      </div>
                    
                    @endif
                    @endforeach
                    <div class="form-group row">
                      <div class="offset-sm-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Salva</button>
                      </div>
                    </div>
                  </form>
                </div>
                <!-- /.tab-pane -->
              </div>
              <!-- /.tab-content -->
            </div><!-- /.card-body -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </div><!-- /.container-fluid -->
  </section>
  <!-- /.content -->
</div>
<!-- /.content-wrapper -->
@endsection