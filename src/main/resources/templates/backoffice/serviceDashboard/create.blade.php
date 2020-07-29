@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Crea permesso</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
                        <li class="breadcrumb-item active">Crea permesso</li>
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
                    <!-- general form elements disabled -->
                    <div class="card card-warning">
                        <div class="card-header">
                            <h3 class="card-title">Tipo permesso</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <form method="POST" action="{{route('createGroupPersist')}}" role="form">
                                @csrf
                                <div class="row">
                                    <div class="col-sm-6">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>Gruppo utente</label>
                                            <input name="name" maxlength="100" type="text" class="form-control"
                                                placeholder="Nome del nuovo gruppo di utenza">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <!-- checkbox -->
                                        <div class="form-group">
                                            <h5>Servizi abilitati</h5>
                                            @if($collection ?? '')
                                            @foreach ($collection as $item)
                                            <div class="icheck-success">
                                                <input id="checkboxSuccess{{$item->id}}" name="service[]" value="{{$item->id}}" class="form-check-input"
                                                    type="checkbox">
                                                    <label for="checkboxSuccess{{$item->id}}">{{$item->name}}</label>
                                            </div>
                                            @endforeach
                                            @endif
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