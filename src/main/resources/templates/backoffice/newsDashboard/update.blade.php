@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1>Aggiorna una news</h1>
        </div>
        <div class="col-sm-6">
          <ol class="breadcrumb float-sm-right">
            <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="{{route('manageNews')}}">Gestione News </a></li>
            <li class="breadcrumb-item active">Aggiorna news</li>
          </ol>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->
  </section>
  @if($item ?? '')
  <!-- Main content -->
  <section class="content">
    <form method="POST" action="{{route('updateNewsPersist')}}" id="myForm" enctype="multipart/form-data">
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
          <div class="alert alert-success alert-dismissible" style="display: none" id="fileC">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-exclamation"></i></h4>
            File selezionati
        </div>
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">News</h3>
              <div class="card-tools">
              <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                  title="Collapse">
                  <i class="fas fa-minus"></i></button>
              </div>
            </div>
            <div class="card-body">
              @csrf
              <input hidden value="{{$item->id}}" name="id">
              <div class="form-group has-error ">
                <label for="inputName">Titolo</label>
                <input required name="name" type="text" value="{{$item->name}}" id="inputName" class="form-control @error('title') is-invalid @enderror">
              </div>
              <div class="form-group ">
                <label for="principalImage">Scegli immagine principale</label>
                <div class="input-group">
                  <div class="custom-file">
                    <label class="custom-file-label" onchange="check()" for="principalImage">Scegli immagine</label>
                    <input  type="file" name="principalImage" class="custom-file-input @error('principalImage') is-invalid @enderror" id="principalImage">
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label for="inputDescription">Testo</label>
                <div class="card-body pad">
                  <div class="mb-3">
                    <p>Si consiglia immagini per un totale di al max 1MB</p>
                    <textarea required name="summernoteInput" class="textarea @error('summernoteInput') is-invalid @enderror"
                  placeholder="Place some text here">{{$item->description}}</textarea>
                  </div>
                </div>
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
          <input type="submit" value="Salva" class="btn btn-success float-right">
        </div>
      </div>
      @endif
    </form>
  </section>
  <!-- /.content -->
</div>
<!-- /.content -->
@endsection