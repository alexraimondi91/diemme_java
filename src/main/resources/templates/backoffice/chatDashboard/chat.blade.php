@extends('backoffice/layout/layout')
@section('content')
<div class="content-wrapper">
    <section class="content-header" id="chatHTML5">
        @error('attachment')
        <!-- warning allert -->
        <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-exclamation"></i></h4>
            Aggiornamento non avvenuto!File non conforme! Si possono caricare solamente file png,jpg o pdf
        </div>
      
        <!-- warning allert -->
        @enderror
        @error('message')
        <!-- warning allert -->
        <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-exclamation"></i></h4>
            Aggiornamento non avvenuto!
        </div>
        <!-- warning allert -->
        @enderror

    </section>
</div>
@endsection
@section('js')
<script src="{{URL::asset('js/ChatHTML5.js')}}"> </script>
<script>
    @if($item ?? '')
    $('#chatHTML5').ready(new ChatHTML5({{  Auth::user()->id  }}, "{{route('chatMessages',$item)}}","{{ csrf_token() }}","{{route('storeChatPersist',$item)}}","{{asset("")}}"));
    @endif 
</script>

@endsection