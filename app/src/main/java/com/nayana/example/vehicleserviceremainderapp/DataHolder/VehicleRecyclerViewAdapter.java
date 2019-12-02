package com.nayana.example.vehicleserviceremainderapp.DataHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nayana.example.vehicleserviceremainderapp.Model.Vehicle;
import com.nayana.example.vehicleserviceremainderapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VehicleRecyclerViewAdapter extends RecyclerView.Adapter<VehicleRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Vehicle> vehicleList;

    public VehicleRecyclerViewAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_row , parent , false);

        return new ViewHolder(view , context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehicle vehicle = vehicleList.get(position);
        String imageURL = null ;

        imageURL = vehicle.getVehicleImage();

        Picasso.with(context)
                .load(imageURL)
                .placeholder(R.drawable.bike_clipart)
                .fit()
                //.centerCrop()
                .into(holder.vehicleImage); //with(context) replaced with get()

        holder.vehicleName.setText( "Vehicle Name  : " + vehicle.getVehicleName());
        holder.vehicleNumber.setText( "Vehicle Number  : " + vehicle.getVehicleNumber());
        holder.recentService.setText( "Recently Vehicle serviced on  : " + vehicle.getRecentServiceDate());
        holder.recentServiceMetreReading.setText( "Recent service metre reading in kms  : " + vehicle.getRecentServiceMetreReading());
        holder.nextService.setText( "Next Vehicle service on  : " + vehicle.getNextServiceDate());
        holder.nextServiceMetreReading.setText( "Next service metre reading at kms  : " + vehicle.getNextServiceMetreReading());
        holder.insuranceDate.setText( "Vehicle Insured Date : " + vehicle.getVehicleInsuranceDate());
        holder.insuranceExpiryDate.setText( "Vehicle Insurance Expires on  : " + vehicle.getVehicleInsuranceExpiryDate());
        holder.engineOil.setText( "Engine Oil Replaced on  : " + vehicle.getEngineOilReplacedDate());
        holder.engineService.setText( "Engine Serviced on  : " + vehicle.getEngineServiceDate());
        holder.chainReplaced.setText( "Chain Replaced on  : " + vehicle.getChainReplacedDate());
        holder.backTyreReplaced.setText( "BackTyre Replaced on : " + vehicle.getBackTyreReplacedDate());
        holder.frontTyreReplaced.setText( "FrontTyre Replaced on  : " + vehicle.getFrontTyreReplacedDate());
        holder.vehicleSeatReplaced.setText( "Vehicle Seat Replaced on  : " + vehicle.getVehicleSeatReplacedDate());
        holder.indicatorReplaced.setText( "Indicator Replaced on  : " + vehicle.getIndicatorReplacedDate());
        holder.headlightReplaced.setText( "Headlight Replaced on  : " + vehicle.getHeadlightReplacedDate());
        holder.aboutVehicle.setText( "About your Vehicle  : " + vehicle.getAboutVehicle());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView vehicleImage;
        public TextView vehicleName;
        public TextView vehicleNumber;
        public TextView recentService;
        public TextView recentServiceMetreReading;
        public TextView nextService;
        public TextView nextServiceMetreReading;
        public TextView insuranceDate;
        public TextView insuranceExpiryDate;

        private TextView engineOil;
        private TextView engineService;
        private TextView chainReplaced;
        private TextView backTyreReplaced;
        private TextView frontTyreReplaced;
        private TextView vehicleSeatReplaced;
        private TextView indicatorReplaced;
        private TextView headlightReplaced;
        private TextView aboutVehicle;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);

            vehicleImage = itemView.findViewById(R.id.vehicleImageList);
            vehicleName = itemView.findViewById(R.id.vehicleNameList);
            vehicleNumber = itemView.findViewById(R.id.vehicleNumberList);
            recentService = itemView.findViewById(R.id.recentServiceDateList);
            recentServiceMetreReading = itemView.findViewById(R.id.recentServiceMetreReadingList);
            nextService = itemView.findViewById(R.id.nextServiceDateList);
            nextServiceMetreReading = itemView.findViewById(R.id.nextServiceMetreReadingList);
            insuranceDate = itemView.findViewById(R.id.InsuredDateList);
            insuranceExpiryDate = itemView.findViewById(R.id.InsuredExpiryDateList);

            engineOil = itemView.findViewById(R.id.engineOilReplacedDateList);
            engineService  = itemView.findViewById(R.id.engineServiceDateList);
            chainReplaced = itemView.findViewById(R.id.chainReplacedDateList);
            backTyreReplaced = itemView.findViewById(R.id.backTyreReplacedDateList);
            frontTyreReplaced = itemView.findViewById(R.id.frontTyreReplacedDateList);
            vehicleSeatReplaced = itemView.findViewById(R.id.vehicleSeatReplacedDateList);
            indicatorReplaced = itemView.findViewById(R.id.indicatorReplacedDateList);
            headlightReplaced = itemView.findViewById(R.id.headlightReplacedDateList);
            aboutVehicle = itemView.findViewById(R.id.aboutVehicleList);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText( ctx , "", Toast.LENGTH_LONG).show();
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //we can start next activity

                    Vehicle vehicle = vehicleList.get(getAdapterPosition());

                    Intent intent = new Intent( context , VehicleDetails.class);

                    intent.putExtra("VehicleImage", vehicle.getVehicleImage());
                    intent.putExtra("VehicleName", vehicle.getVehicleName());
                    intent.putExtra("VehicleNumber", vehicle.getVehicleNumber());
                    intent.putExtra("RecentServiceDate", vehicle.getVehicleName());
                    intent.putExtra("RecentServiceMetreReading", vehicle.getVehicleNumber());
                    intent.putExtra("NextServiceDate", vehicle.getVehicleName());
                    intent.putExtra("NextServiceMetreReading", vehicle.getVehicleNumber());
                    intent.putExtra("VehicleInsuranceDate", vehicle.getVehicleName());
                    intent.putExtra("VehicleInsuranceExpiryDate", vehicle.getVehicleNumber());
                    intent.putExtra("EngineOilReplacedDate", vehicle.getVehicleName());
                    intent.putExtra("EngineServiceDate", vehicle.getVehicleNumber());
                    intent.putExtra("ChainReplacedDate", vehicle.getVehicleName());
                    intent.putExtra("BackTyreReplacedDate", vehicle.getVehicleNumber());
                    intent.putExtra("FrontTyreReplacedDate", vehicle.getVehicleName());
                    intent.putExtra("VehicleSeatReplacedDate", vehicle.getVehicleNumber());
                    intent.putExtra("HeadlightReplacedDate", vehicle.getVehicleName());
                    intent.putExtra("IndicatorReplacedDate", vehicle.getVehicleName());
                    intent.putExtra("AboutVehicle", vehicle.getAboutVehicle());

                    Log.d( "VehicleRVA : " ,
                                "VehicleName() : "+ vehicle.getVehicleName()
                                        +" VehicleNumber() : "+ vehicle.getVehicleNumber()
                                        +" RecentServiceDate() : "+ vehicle.getRecentServiceDate()
                                        +" RecentServiceMetreReading() : "+ vehicle.getRecentServiceMetreReading()
                                        +" NextServiceDate() : "+ vehicle.getNextServiceDate()
                                        +" NextServiceMetreReading() : "+ vehicle.getNextServiceMetreReading()
                                        +" VehicleInsuranceDate() : "+ vehicle.getVehicleInsuranceDate()
                                        +" VehicleInsuranceExpiryDate() : "+ vehicle.getVehicleInsuranceExpiryDate()
                                        +" VehicleImage() : "+ vehicle.getVehicleImage()
                                        +" EngineOilReplacedDate() : "+vehicle.getEngineOilReplacedDate()
                                        +" EngineServiceDate() : "+vehicle.getEngineServiceDate()
                                        +" ChainReplacedDate() : "+vehicle.getChainReplacedDate()
                                        +" BackTyreReplacedDate() : "+vehicle.getBackTyreReplacedDate()
                                        +" FrontTyreReplacedDate() : "+vehicle.getFrontTyreReplacedDate()
                                        +" VehicleSeatReplacedDate() : "+vehicle.getVehicleSeatReplacedDate()
                                        +" HeadlightReplacedDate() : "+vehicle.getHeadlightReplacedDate()
                                        +" IndicatorReplacedDate() : "+vehicle.getIndicatorReplacedDate());

                    ctx.startActivity(intent);
                }
            });
        }
    }
}
