@extends('backoffice/layout/layout')
@section('content')
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Progetto Layout</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="{{route('dashboard')}}">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="{{route('showLayouts')}}">Gestione Layouts</a></li>
                    <li class="breadcrumb-item active">Progetto Layout</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        @if($item ?? '')
        
        <!-- Default box -->
        <div class="card card-solid">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-sm-6">
                    <h3 class="d-inline-block d-sm-none">{{$item->name}}</h3>
                    <div class="col-12">
                    @foreach ($photos as $key=>$photo)
                    @if ($key==0)
                        <img src="{{asset($photo->path)}}" class="product-image" alt="Product Image">
                        <div class="col-12 product-image-thumbs">
                        <div class="product-image-thumb active"><img src="{{asset($photo->path)}}" alt="Product Image"></div>
                    @else
                        <div class="product-image-thumb" ><img src="{{asset($photo->path)}}" alt="Product Image"></div>
                    @endif
                    @endforeach
                        </div>
                        </div>
                        
                    </div>
                    <div class="col-12 col-sm-6">
                        <h3 class="my-3">{{$item->name}}</h3> 
                        @if($users ?? '') @foreach ($users as $user)
                        <i class="fas fa-user"></i>
                            {{$user->name_user}} {{$user->surname_user}} &nbsp;
                        @endforeach @endif
                        <hr>
                        <p>{{$item->description}}</p>
                    </div>
                </div>
            </div>
            <!-- /.card-body -->
        </div>
        <!-- /.card -->
        @endif
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
@endsection