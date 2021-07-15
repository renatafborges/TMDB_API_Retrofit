package com.renata.tmdb_api_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovies : RecyclerView
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loading = findViewById(R.id.loading)
        rvMovies = findViewById(R.id.rvMovies)

        //variáveis para fazer as chamadas
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(getString(R.string.api_key))

        //enviando o request para  network
        //passamos uma callback que vai retornar no formato de popularmovies object
        call.enqueue(object : Callback<PopularMovies>{

            // a chamada é retornada coletando dados adicionando ao recyclerview
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){
                    loading.visibility = View.GONE
                    rvMovies.apply {
                        setHasFixedSize(true) //significa que o RecyclerView possui filhos (itens) com largura e altura fixas.
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }
            }
            //
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
