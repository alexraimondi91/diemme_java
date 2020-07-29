@extends('backoffice/layout/layout')
@section('content')
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Contatti</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
                        <li class="breadcrumb-item active">Aggiorna Contatti</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <!-- Main content -->
    @if($collection ?? '')
    @foreach ($collection as $item)
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements disabled -->
                    <div class="card card-warning">
                        <div class="card-header">
                            <h3 class="card-title">Contatti</h3>
                        </div>

                    <form action="{{route('updateContactPersist')}}" role="form" method="POST" id="myForm">
                        @csrf
                        <!-- /.card-header -->
                        <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Slogan</label>
                                        <input required type="text" name="name" class="form-control" value="{{$item->name}}">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- textarea -->
                                        <div class="form-group">
                                            <label>Descrizione Società</label>
                                        <textarea required class="form-control" name="text" rows="3">{{$item->text}}</textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Email</label>
                                        <input required type="email" maxlength="40" name="email" class="form-control" value="{{$item->email}}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Telefono</label>
                                        <input type="tel" maxlength="40" name="number" class="form-control" value="{{$item->number}}">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Regione</label>
                                        <input required type="text"  class="form-control" name="region" value="{{$item->region}}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Nazione</label>
                                            <input required type="text" class="form-control" name="nation" value="{{$item->nation}}">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Via</label>
                                            <input required type="text"  class="form-control" name="street" value="{{$item->street}}">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Latitudine</label>
                                            <input required type="text" maxlength="40" name="lat" class="form-control" value="{{$item->lat}}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Logitudine</label>
                                            <input  required type="text" maxlength="40" name="long" class="form-control" value="{{$item->long}}">
                                        </div>
                                    </div>
                                </div>
                                <!-- input states -->
                            
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
                <!--/.col (right) -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-12">
                  <a href="javascript:document.getElementById('myForm').reset();" class="btn btn-secondary">Annulla</a>
                  <input type="submit" value="Aggiorna" class="btn btn-success float-right">
                </div>
            </div>
        </form>
        </div>
        <!-- /.container-fluid -->
    </section>
    @endforeach
    @endif
    <!-- /.content -->
</div>
@endsection