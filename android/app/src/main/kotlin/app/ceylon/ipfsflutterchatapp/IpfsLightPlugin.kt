package app.ceylon.ipfsflutterchatapp

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodChannel
import io.textile.ipfslite.Peer
import java.nio.charset.Charset


class IpfsLightPlugin : FlutterPlugin {
    private val CHANNEL = "flutter_ipfs_client"
    private lateinit var litePeer: Peer

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {

        val debug = false


        MethodChannel(binding.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->

            if (call.method == "init") {
                litePeer = Peer(binding.applicationContext.filesDir.path, debug)
                Peer.start()
                result.success(litePeer.started())
            } else if (call.method == "add_data") {
                val data = call.argument<String>("data")
                val fileCid = litePeer.addFileSync(data!!.toByteArray(Charset.forName("UTF-8")))
                result.success(fileCid);
            } else if (call.method == "get_data") {
                val cid = call.argument<String>("cid")
                val data = litePeer.getFileSync(cid)
                result.success(String(data, Charset.forName("UTF-8")))
            }
        }


    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    }


}