@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Gestione Chat</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
                        <li class="breadcrumb-item active">Gestione Messaggi</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Lista Conversazioni</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Cognome</th>
                                        <th>Nome</th>
                                        <th>Tipo</th>
                                        <th>Progetto</th>
                                        <th>Data Aggiornameto</th>
                                        <th style="width: 40px">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @if($collection ?? '' )
                                    @foreach ($collection as $item)
                                    <tr>
                                        <td>
                                            {{$item->user()->first()->surname_user}}
                                        </td>
                                        <td>
                                            {{$item->user()->first()->name_user}}
                                        </td>
                                        <td>
                                            {{$item->user()->first()->group->name}}
                                        </td>
                                        <td>
                                            {{$item->subject}}
                                        </td>
                                        <td>
                                            {{$item->created_at}}
                                        </td>
                                        <td>
                                            <form method="POST" action="{{route('deleteChat')}}">
                                                @csrf
                                                <input name="id" hidden value="{{$item->id}}">
                                                <button type="submit" class="btn btn-danger">
                                                    Cancella
                                                </button>
                                            </form>
                                            <hr>
                                            <form method="GET" action="{{route('chatView')}}">
                                                <input name="id" hidden value="{{$item->id}}">
                                                <button type="submit" class="btn btn-primary">
                                                    Visualizza
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    @endforeach

                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                {{$collection->links()}}
                            </ul>
                        </div>
                        @endif
                    </div>
                    <!-- /.card -->
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