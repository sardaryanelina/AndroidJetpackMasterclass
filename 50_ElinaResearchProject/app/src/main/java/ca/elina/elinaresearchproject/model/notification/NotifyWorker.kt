package ca.elina.elinaresearchproject.model.notification

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO Step 2: Create a new package as "notification" and a class as NotifyWorker as below. Extent the Worker class with required params.
// START
class NotifyWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    // TODO Step 3: Override the doWork function. This function will be executed when the work scheduler is triggered. You can add your code that you want to execute periodically.
    // START
    override fun doWork(): Result {

        Log.i("Notify Worker", "doWork function is called...")

        return success()
    }
    // END
}