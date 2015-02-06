package com.timethor.terracontrol.core.custom.object.bo4;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.custom.object.Branch;
import com.timethor.terracontrol.core.custom.object.BranchFunctionObject;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate;
import com.timethor.terracontrol.core.custom.object.Rotation;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Timethor
 */
public class BranchFunction extends BO4Function implements Branch {

    /**
     */
    public int x;
    /**
     */
    public int y;
    /**
     */
    public int z;
    /**
     */
    public int totalChance;
    /**
     */
    public SortedSet<BranchFunctionObject> branches;

    @Override
    public BranchFunction rotate() {
        BranchFunction rotatedBranch = new BranchFunction();
        rotatedBranch.x = z;
        rotatedBranch.y = y;
        rotatedBranch.z = -x;
        rotatedBranch.branches = branches;
        for (Iterator<BranchFunctionObject> it = branches.iterator(); it.hasNext();) {
            BranchFunctionObject holder = it.next();
            rotatedBranch.branches.add(new BranchFunctionObject(Rotation.next(holder.getRotation()), holder.getChance(), holder.getBranch()));
        }

        return rotatedBranch;
    }

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        // @TODO: add a max chance option to the end of the Branch declaration like:
        // Branch(x,y,z,branchName,rotation,chance[,anotherBranchName,rotation,chance[,...]][,maxChanceOutOf])
        // Where maxChanceOutOf is the maximum of the random number to spawn, ie. from 0 to maxChanceOutOf
        assureSize(6, args);

        x = readInt(args.get(0), -32, 32);
        y = readInt(args.get(1), -64, 64);
        z = readInt(args.get(2), -32, 32);
        totalChance = 0;
        branches = new TreeSet<>(BranchFunctionObject.getComparator());
        for (int i = 3; i < args.size() - 2; i += 3) {
            CustomObject object = getHolder().otherObjectsInDirectory.get(args.get(i).toLowerCase());
            if (object == null) {
                throw new InvalidConfigException("The branch " + args.get(i) + " was not found. Make sure to place it in the same directory.");
            }
            int branchChance = readInt(args.get(i + 2), 1, 100);
            totalChance += branchChance;
            // CustomObjects are inserted into the Set in ascending chance order wtih Chance being cumulative.
            branches.add(new BranchFunctionObject(Rotation.getRotation(args.get(i + 1)), totalChance, object));
        }
    }

    @Override
    public String makeString() {
        String output = "Branch(" + x + "," + y + "," + z;
        for (Iterator<BranchFunctionObject> it = branches.iterator(); it.hasNext();) {
            BranchFunctionObject holder = it.next();
            output += "," + holder.getBranch().getName() + "," + holder.getRotation() + "," + holder.getChance();
        }
        return output + ")";
    }

    @Override
    public CustomObjectCoordinate toCustomObjectCoordinate(TerraWorld world, Random random, int x, int y, int z) {
        int rand = random.nextInt(totalChance >= 100 ? totalChance : 100);
        for (Iterator<BranchFunctionObject> it = branches.iterator(); it.hasNext();) {
            BranchFunctionObject holder = it.next();
            if (holder.getChance() > rand) {
                return new CustomObjectCoordinate(holder.getBranch(), holder.getRotation(), x + this.x, y + this.y, z + this.z);
            }
        }
        return null;
    }
}
