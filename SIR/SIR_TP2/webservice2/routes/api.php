<?php

use App\Conteudo;
use App\User;
use Illuminate\Support\Facades\Route;

Route::post('/cadastro', "UsuarioController@cadastro");
Route::post('/login', "UsuarioController@login");
Route::middleware('auth:api')->put('/perfil', "UsuarioController@perfil");

Route::middleware('auth:api')->post('/conteudo/adicionar', "ConteudoController@adicionar");
Route::middleware('auth:api')->get('/conteudo/lista', "ConteudoController@lista");
Route::middleware('auth:api')->put('/conteudo/curtir/{id}', "ConteudoController@curtir");
Route::middleware('auth:api')->put('/conteudo/curtirpagina/{id}', "ConteudoController@curtirpagina");
Route::middleware('auth:api')->put('/conteudo/comentar/{id}', "ConteudoController@comentar");
Route::middleware('auth:api')->put('/conteudo/comentarpagina/{id}', "ConteudoController@comentarpagina");

Route::middleware('auth:api')->get('/conteudo/pagina/lista/{id}', "ConteudoController@pagina");

Route::middleware('auth:api')->post('/usuario/amigo', "UsuarioController@amigo");
Route::middleware('auth:api')->get('/usuario/listaAmigos', "UsuarioController@listaAmigos");
Route::middleware('auth:api')->get('/usuario/listaAmigosPagina/{id}', "UsuarioController@listaAmigosPagina");

Route::middleware('auth:api')->get('/usuario/allUsers', "UsuarioController@allUsers");

Route::get('/testes', function () {
	$user = User::find(1);
	$user2 = User::find(5);

	$conteudos = Conteudo::all();

	//Limpar conteudos
	/*
	    foreach($conteudos as $key => $value){
	        $value->delete();
*/

	//Criar conteudo
	/*
	    $user->conteudos()->create([
	        'titulo' => 'Conteúdo 2',
	        'texto' => 'Aqui o texto do conteúdo',
	        'imagem' => 'url da imagem',
	        'link' => 'Link',
	        'data' => '2020-09-25'
	    ]);
	    return $user->conteudos;
*/

	//Adicionar amigos
	/*
	    $user->amigos()->toggle($user2->id);
	    $user->amigos()->detach($user2->id);
	    $user->amigos()->toggle($user2->id);

*/

	//Adicionar curtidas
	/*
	    $conteudo = Conteudo::find(1);
	    $user2->curtidas()->toggle($conteudo->id);

	    //return $conteudo->curtidas()->count();
*/

	//Novo comentario

	$conteudo = Conteudo::find(7);
	$user->comentarios()->create([
		'conteudo_id' => $conteudo->id,
		'texto' => 'Comentario 1',
		'data' => date('Y-m-d'),
	]);
	$user2->comentarios()->create([
		'conteudo_id' => $conteudo->id,
		'texto' => 'Comentario 2',
		'data' => date('Y-m-d H:i:s'),
	]);
	return $conteudo->comentarios;
});
