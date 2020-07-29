@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
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
            @error('file.*')
            <!-- warning allert -->
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-exclamation"></i></h4>
                Aggiornamento avvenuto!File non conforme!
            </div>
            <!-- warning allert -->
            @enderror
            @error('user_id')
            <!-- warning allert -->
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-exclamation"></i></h4>
                Aggiornamento avvenuto! Utente non valido!
            </div>
            <!-- warning allert -->
            @enderror
            @error('description')
            <!-- warning allert -->
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-exclamation"></i></h4>
                Aggiornamento avvenuto! Descrizione non valida!
            </div>
            <!-- warning allert -->
            @enderror
            @if($success ?? '')
            <!-- warning allert -->
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-exclamation"></i></h4>
                Aggiornamento avvenuto!
            </div>
            <!-- warning allert -->
            @endif
            <div class="alert alert-success alert-dismissible" style="display: none" id="fileC">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-exclamation"></i></h4>
                File selezionati
            </div>
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Layout</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="{{route('manageLayout')}}">Gestione Layout </a></li>
                        <li class="breadcrumb-item active">Aggiorna layout</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    @if($item ?? '')
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="card card-primary">
                    <div class="card-header">
                        <h3 class="card-title">Progetto di @if($owners ?? '')
                            @foreach($owners as $owner)
                            @if($owner->id != Auth::user()->id)
                            {{$owner->name_user}} {{$owner->surname_user}} #ID {{$owner->id}}
                            @endif
                            @endforeach
                            @endif
                        </h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <form action="{{route('updateLayoutPersist')}}" method="POST" enctype="multipart/form-data">
                        @csrf
                        <input name="id" value="{{$item->id}}" hidden>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="inputName">Nome del progetto</label>
                                <input required type="text" value="{{$item->name}}" name="name" id="inputName"
                                    class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Passa il progetto a</label>
                                <select name="owner" class="form-control select2" style="width: 100%;">
                                    <option value="{{Auth::user()->id}}" selected="selected">nessuno</option>
                                    @if($users ?? '')
                                    @foreach ($users as $user)
                                <option value="{{$user->id}}">{{$user->name_user}} {{$user->surname_user}} </option>
                                    @endforeach
                                    @endif
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputDescription">Breve Descrizione</label>
                                <textarea id="inputDescription" required name="description" class="form-control"
                                    rows="4">{{$item->description}}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputFile">Immagini di Layout</label>
                                <div class="input-group">
                                    <div class="custom-file">
                                        <input type="file" name="file[]" onchange="check()" multiple class="custom-file-input"
                                            id="exampleInputFile">
                                        <label class="custom-file-label" for="exampleInputFile">Scegli i file d'immagine
                                            da caricare</label>
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
                <input type="submit" value="Salva" class="btn btn-success float-right"></form>
            @if(Auth::user()->sendToProduction())
            <form action="{{route('sendIntoProduction')}}" method="POST">
                @csrf
                <input hidden name="id" value="{{$item->id}}">
                <input type="submit" value="Manda in Produzione" class="btn btn-primary float-left">
            </form>
            @endif
            </div>
        </div>
        
    </section>
    @endif
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
@endsection