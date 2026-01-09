package com.mecanotun.mobile.databinding;
import com.mecanotun.mobile.R;
import com.mecanotun.mobile.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityTimeSlotSelectionBindingImpl extends ActivityTimeSlotSelectionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_title, 4);
        sViewsWithIds.put(R.id.tv_available_label, 5);
        sViewsWithIds.put(R.id.rv_time_slots, 6);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityTimeSlotSelectionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ActivityTimeSlotSelectionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.Button) bindings[3]
            , (android.widget.ProgressBar) bindings[1]
            , (androidx.recyclerview.widget.RecyclerView) bindings[6]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            );
        this.btnConfirmSelection.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar.setTag(null);
        this.tvError.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.mecanotun.mobile.viewmodel.TimeSlotSelectionViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.mecanotun.mobile.viewmodel.TimeSlotSelectionViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelSelectedSlot((androidx.lifecycle.LiveData<com.mecanotun.mobile.api.TimeSlotDto>) object, fieldId);
            case 1 :
                return onChangeViewModelErrorMessage((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelIsLoading((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelSelectedSlot(androidx.lifecycle.LiveData<com.mecanotun.mobile.api.TimeSlotDto> ViewModelSelectedSlot, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelErrorMessage(androidx.lifecycle.LiveData<java.lang.String> ViewModelErrorMessage, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsLoading(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        androidx.lifecycle.LiveData<com.mecanotun.mobile.api.TimeSlotDto> viewModelSelectedSlot = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = false;
        java.lang.String viewModelErrorMessageGetValue = null;
        com.mecanotun.mobile.api.TimeSlotDto viewModelSelectedSlotGetValue = null;
        boolean viewModelErrorMessageJavaLangObjectNull = false;
        int viewModelErrorMessageJavaLangObjectNullViewVISIBLEViewGONE = 0;
        int viewModelIsLoadingViewVISIBLEViewGONE = 0;
        boolean viewModelSelectedSlotJavaLangObjectNull = false;
        java.lang.Boolean viewModelIsLoadingGetValue = null;
        boolean viewModelSelectedSlotJavaLangObjectNullViewModelIsLoadingBooleanFalse = false;
        androidx.lifecycle.LiveData<java.lang.String> viewModelErrorMessage = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsLoading = null;
        com.mecanotun.mobile.viewmodel.TimeSlotSelectionViewModel viewModel = mViewModel;
        boolean ViewModelIsLoading1 = false;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x1dL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.selectedSlot
                        viewModelSelectedSlot = viewModel.getSelectedSlot();
                    }
                    updateLiveDataRegistration(0, viewModelSelectedSlot);


                    if (viewModelSelectedSlot != null) {
                        // read viewModel.selectedSlot.getValue()
                        viewModelSelectedSlotGetValue = viewModelSelectedSlot.getValue();
                    }


                    // read viewModel.selectedSlot.getValue() != null
                    viewModelSelectedSlotJavaLangObjectNull = (viewModelSelectedSlotGetValue) != (null);
                if((dirtyFlags & 0x1dL) != 0) {
                    if(viewModelSelectedSlotJavaLangObjectNull) {
                            dirtyFlags |= 0x400L;
                    }
                    else {
                            dirtyFlags |= 0x200L;
                    }
                }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.errorMessage
                        viewModelErrorMessage = viewModel.getErrorMessage();
                    }
                    updateLiveDataRegistration(1, viewModelErrorMessage);


                    if (viewModelErrorMessage != null) {
                        // read viewModel.errorMessage.getValue()
                        viewModelErrorMessageGetValue = viewModelErrorMessage.getValue();
                    }


                    // read viewModel.errorMessage.getValue() != null
                    viewModelErrorMessageJavaLangObjectNull = (viewModelErrorMessageGetValue) != (null);
                if((dirtyFlags & 0x1aL) != 0) {
                    if(viewModelErrorMessageJavaLangObjectNull) {
                            dirtyFlags |= 0x40L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                    }
                }


                    // read viewModel.errorMessage.getValue() != null ? View.VISIBLE : View.GONE
                    viewModelErrorMessageJavaLangObjectNullViewVISIBLEViewGONE = ((viewModelErrorMessageJavaLangObjectNull) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isLoading
                        viewModelIsLoading = viewModel.isLoading();
                    }
                    updateLiveDataRegistration(2, viewModelIsLoading);


                    if (viewModelIsLoading != null) {
                        // read viewModel.isLoading.getValue()
                        viewModelIsLoadingGetValue = viewModelIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsLoadingGetValue);
                if((dirtyFlags & 0x1cL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) {
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? View.VISIBLE : View.GONE
                    viewModelIsLoadingViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished

        if ((dirtyFlags & 0x400L) != 0) {

                if (viewModel != null) {
                    // read viewModel.isLoading
                    viewModelIsLoading = viewModel.isLoading();
                }
                updateLiveDataRegistration(2, viewModelIsLoading);


                if (viewModelIsLoading != null) {
                    // read viewModel.isLoading.getValue()
                    viewModelIsLoadingGetValue = viewModelIsLoading.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue())
                androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsLoadingGetValue);
            if((dirtyFlags & 0x1cL) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) {
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x80L;
                }
            }


                // read !androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue())
                ViewModelIsLoading1 = !androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue;
        }

        if ((dirtyFlags & 0x1dL) != 0) {

                // read viewModel.selectedSlot.getValue() != null ? !androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) : false
                viewModelSelectedSlotJavaLangObjectNullViewModelIsLoadingBooleanFalse = ((viewModelSelectedSlotJavaLangObjectNull) ? (ViewModelIsLoading1) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x1dL) != 0) {
            // api target 1

            this.btnConfirmSelection.setEnabled(viewModelSelectedSlotJavaLangObjectNullViewModelIsLoadingBooleanFalse);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            this.progressBar.setVisibility(viewModelIsLoadingViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvError, viewModelErrorMessageGetValue);
            this.tvError.setVisibility(viewModelErrorMessageJavaLangObjectNullViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.selectedSlot
        flag 1 (0x2L): viewModel.errorMessage
        flag 2 (0x3L): viewModel.isLoading
        flag 3 (0x4L): viewModel
        flag 4 (0x5L): null
        flag 5 (0x6L): viewModel.errorMessage.getValue() != null ? View.VISIBLE : View.GONE
        flag 6 (0x7L): viewModel.errorMessage.getValue() != null ? View.VISIBLE : View.GONE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? View.VISIBLE : View.GONE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? View.VISIBLE : View.GONE
        flag 9 (0xaL): viewModel.selectedSlot.getValue() != null ? !androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) : false
        flag 10 (0xbL): viewModel.selectedSlot.getValue() != null ? !androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) : false
    flag mapping end*/
    //end
}