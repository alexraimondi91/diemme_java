@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1>Aggiungi una nuova istruzione di preventivo</h1>
        </div>
        <div class="col-sm-6">
          <ol class="breadcrumb float-sm-right">
            <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
            <li class="breadcrumb-item active">Aggiungi istruzione</li>
          </ol>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->
  </section>
  <!-- Main content -->
  <section class="content">
    <form method="POST" action="{{route('createQuotationPersist')}}" id="myForm" enctype="multipart/form-data">
      <div class="row">
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
          @if($warning ?? '')
          <!-- warning allert -->
          <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Avviso!</h4>
            Caricamento non avvenuto.
          </div>
          <!-- warning allert -->
          @endif
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Istruzione</h3>
              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                  title="Collapse">
                  <i class="fas fa-minus"></i></button>
              </div>
            </div>
            <div class="card-body">
              @csrf
              <div class="form-group  @error('title') has-error @enderror">
                <label for="inputName">Titolo</label>
                <input required name="title" type="text" id="inputName" class="form-control">
              </div>
              <div class="form-group @error('description') has-error @enderror">
                <label>Descrizione</label>
                <textarea class="form-control" name="description" rows="3" placeholder="Descrizione...."></textarea>
              </div>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <a href="javascript:document.getElementById('myForm').reset();" class="btn btn-secondary">Annulla</a>
          <input type="submit" value="Aggiungi" class="btn btn-success float-right">
        </div>
      </div>
    </form>
  </section>
  <!-- /.content -->
</div>
<!-- /.content -->
@endsection