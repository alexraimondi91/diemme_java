@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Gestione Utenti</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
                        <li class="breadcrumb-item active">Gestione Utenti</li>
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
                            <h3 class="card-title">Lista Utenti</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table  class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th style="width: 10px">#</th>
                                        <th>Nome</th>
                                        <th>Cognome</th>
                                        <th>email</th>
                                        <th>Tipo utente</th>
                                        <th>Data Creazione</th>
                                        <th>Codice Fiscale</th>
                                        <th>Indirizzo</th>
                                        <th style="width: 40px">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @if($collection ?? '')
                                    @foreach ($collection as $item)
                                    @if($item->id != Auth::user()->id)
                                    <tr>
                                        <td>{{$item->id}}</td>
                                        <td>{{$item->name_user}}</td>
                                        <td>{{$item->surname_user}}</td>
                                        <td>{{$item->email}}</td>
                                        <td>{{$item->group->name}}</td>
                                        <td>{{$item->created_at->diffForHumans()}}</td>
                                        <td>{{$item->fiscalCode_user}}</td>
                                        <td>{{$item->address_user}}, {{$item->region_user}}, {{$item->country_user}}</td>
                                        <td>
                                            <form method="POST" action="{{route('deleteUser')}}">
                                                @csrf
                                                <input name="id" hidden value="{{$item->id}}">
                                                <button type="submit" class="btn btn-danger">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </form>
                                            <hr>
                                            <form method="GET" action="{{route('updateUser')}}">
                                                <input name="id" hidden value="{{$item->id}}">
                                                <button type="submit" class="btn btn-secondary">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    @endif
                                    @endforeach
                                    @endif
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                {{$collection->links()}}
                            </ul>
                        </div>
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