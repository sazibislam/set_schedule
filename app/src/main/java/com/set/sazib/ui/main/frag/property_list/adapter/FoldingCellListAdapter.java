package com.set.sazib.ui.main.frag.property_list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.set.sazib.R;
import com.set.sazib.data.api.response.PropertyDataResponse;
import com.set.sazib.utils.view.folding.FoldingCell;

import java.util.HashSet;
import java.util.List;

public class FoldingCellListAdapter extends ArrayAdapter<PropertyDataResponse> {

  private final HashSet<Integer> unfoldedIndexes = new HashSet<>();
  private View.OnClickListener defaultRequestBtnClickListener;

  public FoldingCellListAdapter(Context context, List<PropertyDataResponse> objects) {
    super(context, 0, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    PropertyDataResponse propertyDataResponse = getItem(position);
    FoldingCell cell = (FoldingCell) convertView;
    ViewHolder viewHolder;
    if (cell == null) {
      viewHolder = new ViewHolder();
      LayoutInflater vi = LayoutInflater.from(getContext());
      cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
      viewHolder.price = cell.findViewById(R.id.title_price);
      viewHolder.time = cell.findViewById(R.id.title_time_label);
      viewHolder.date = cell.findViewById(R.id.title_date_label);
      viewHolder.fromAddress = cell.findViewById(R.id.title_from_address);
      viewHolder.toAddress = cell.findViewById(R.id.title_to_address);
      viewHolder.requestsCount = cell.findViewById(R.id.title_requests_count);
      viewHolder.pledgePrice = cell.findViewById(R.id.title_pledge);
      viewHolder.contentRequestBtn = cell.findViewById(R.id.content_request_btn);
      viewHolder.title_weight = cell.findViewById(R.id.title_weight);

      viewHolder.content_name_view = cell.findViewById(R.id.content_name_view);
      viewHolder.streetAddress = cell.findViewById(R.id.streetAddress);
      viewHolder.city = cell.findViewById(R.id.city);
      viewHolder.ownerOccupied = cell.findViewById(R.id.ownerOccupied);

      viewHolder.area_acres = cell.findViewById(R.id.area_acres);
      viewHolder.building_count = cell.findViewById(R.id.building_count);
      viewHolder.county_land_use_code = cell.findViewById(R.id.county_land_use_code);
      viewHolder.county_name = cell.findViewById(R.id.county_name);
      viewHolder.county_land_use_description = cell.findViewById(R.id.county_land_use_description);
      viewHolder.legal_description = cell.findViewById(R.id.legal_description);
      viewHolder.location_descriptions = cell.findViewById(R.id.location_descriptions);
      viewHolder.municipality = cell.findViewById(R.id.municipality);

      cell.setTag(viewHolder);
    } else {
      if (unfoldedIndexes.contains(position)) {
        cell.unfold(true);
      } else {
        cell.fold(true);
      }
      viewHolder = (ViewHolder) cell.getTag();
    }

    if (null == propertyDataResponse) {
      return cell;
    }

    String address = propertyDataResponse.getPropertyData().getAddress().getCity() + ", "
        + propertyDataResponse.getPropertyData().getAddress().getState();
    viewHolder.fromAddress.setText(propertyDataResponse.getPropertyData().getAddress().getFormattedStreetAddress());
    viewHolder.toAddress.setText(address);

    int price = 0;
    for (int i = 0; i < propertyDataResponse.getPropertyData().getTaxes().size(); i++) {
      price += propertyDataResponse.getPropertyData().getTaxes().get(i).getAmount();
    }
    viewHolder.price.setText("$" + price);

    int improvedValue = 0;
    int landValue = 0;
    int totalValue = 0;
    for (int i = 0; i < propertyDataResponse.getPropertyData().getTaxes().size(); i++) {
      improvedValue +=
          propertyDataResponse.getPropertyData().getAssessments().get(i).getImprovementValue();
      landValue += propertyDataResponse.getPropertyData().getAssessments().get(i).getLandValue();
      totalValue += propertyDataResponse.getPropertyData().getAssessments().get(i).getTotalValue();
    }
    viewHolder.requestsCount.setText("$" + improvedValue);
    viewHolder.pledgePrice.setText("$" + landValue);
    viewHolder.title_weight.setText("$" + totalValue);

    viewHolder.ownerOccupied.setText("Owner Occupied "+propertyDataResponse.getPropertyData().getOwner().getOwnerOccupied());

    viewHolder.content_name_view.setText(propertyDataResponse.getPropertyData().getOwner().getName());
    viewHolder.streetAddress.setText(propertyDataResponse.getPropertyData().getOwner().getFormattedStreetAddress());
    viewHolder.city.setText(propertyDataResponse.getPropertyData().getOwner().getCity() + ", "
        + propertyDataResponse.getPropertyData().getOwner().getState());
    viewHolder.area_acres.setText("Area(acr) "+ propertyDataResponse.getPropertyData().getParcel().getAreaAcres().toString());
    //viewHolder.building_count.setText("building count "+ propertyDataResponse.getPropertyData().getParcel().getBuildingCount().toString());
    viewHolder.county_land_use_code.setText("county land use code "+ propertyDataResponse.getPropertyData().getParcel().getCountyLandUseCode());
    viewHolder.county_name.setText(propertyDataResponse.getPropertyData().getParcel().getCountyName());
    viewHolder.county_land_use_description.setText(propertyDataResponse.getPropertyData().getParcel().getCountyLandUseDescription());
    viewHolder.legal_description.setText(propertyDataResponse.getPropertyData().getParcel().getCountyLandUseDescription());
    viewHolder.municipality.setText(propertyDataResponse.getPropertyData().getParcel().getMunicipality());

    return cell;
  }

  public void registerToggle(int position) {
    if (unfoldedIndexes.contains(position)) {
      registerFold(position);
    } else {
      registerUnfold(position);
    }
  }

  public void registerFold(int position) {
    unfoldedIndexes.remove(position);
  }

  public void registerUnfold(int position) {
    unfoldedIndexes.add(position);
  }

  public View.OnClickListener getDefaultRequestBtnClickListener() {
    return defaultRequestBtnClickListener;
  }

  public void setDefaultRequestBtnClickListener(
      View.OnClickListener defaultRequestBtnClickListener) {
    this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
  }

  private static class ViewHolder {
    TextView price;
    TextView contentRequestBtn;
    TextView pledgePrice;
    TextView fromAddress;
    TextView toAddress;
    TextView requestsCount;
    TextView date;
    TextView time;
    TextView title_weight;
    TextView content_name_view;
    TextView streetAddress;
    TextView city;
    TextView ownerOccupied;

    TextView area_acres;
    TextView building_count;
    TextView county_land_use_code;
    TextView county_name;
    TextView county_land_use_description;
    TextView legal_description;
    TextView location_descriptions;
    TextView municipality;
  }
}
