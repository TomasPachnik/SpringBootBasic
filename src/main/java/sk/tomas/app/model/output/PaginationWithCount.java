package sk.tomas.app.model.output;

import java.util.List;

/**
 * Created by Tomas Pachnik on 16-Jan-17.
 */
public class PaginationWithCount {

    private long count;
    private List<IdentityOutput> identityOutputs;

    public PaginationWithCount(long count, List<IdentityOutput> identityOutputs) {
        this.count = count;
        this.identityOutputs = identityOutputs;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<IdentityOutput> getIdentityOutputs() {
        return identityOutputs;
    }

    public void setIdentityOutputs(List<IdentityOutput> identityOutputs) {
        this.identityOutputs = identityOutputs;
    }
}
