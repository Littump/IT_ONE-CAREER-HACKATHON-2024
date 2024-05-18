import BackLink from "@/ui/BackLink.tsx";
import { Operations } from "@/modules/Operations";

export default function OperationsInfoPage() {
  return (
    <div className="flex flex-col gap-6">
      <BackLink to="/"></BackLink>
      {/*<ExpensesGraph />*/}
      {/*<IncomesGraph />*/}
      <Operations isFull={false} />
    </div>
  );
}
