import { RouterProvider, createBrowserRouter } from "react-router-dom";
import LoginPage from "./pages/LoginPage.tsx";
import Layout from "./ui/Layout.tsx";
import DashboardPage from "@/pages/Dashboard.tsx";
import AddGoalChoosePage from "@/pages/goals/AddGoalChoosePage.tsx";
import AddGoalFormPage from "@/pages/goals/AddGoalFormPage.tsx";
import GoalPage from "@/pages/goals/GoalPage.tsx";
import EditGoalFormPage from "@/pages/goals/EditGoalFormPage.tsx";
import GoalsInfoPage from "@/pages/goals/GoalsInfoPage.tsx";
import OperationsInfoPage from "@/pages/operations/OperationsInfoPage.tsx";
import OperationPage from "@/pages/operations/OperationPage.tsx";
import AddOperationChooseTypePage from "@/pages/operations/AddOperationChooseTypePage.tsx";
import AddOperationChooseKindPage from "@/pages/operations/AddOperationChooseKindPage.tsx";
import AddOperationFormPage from "@/pages/operations/AddOperationFormPage.tsx";
import InfoPage from "@/pages/InfoPage.tsx";
import ProfilePage from "@/pages/ProfilePage.tsx";
import AddOperationChooseIsPeriodic from "@/modules/Operations/ui/AddOperationChooseIsPeriodic.tsx";
import IntroPage from "@/pages/IntroPage.tsx";
import RegistrationPage from "@/pages/RegistrationPage.tsx";

const goalsRouter = [
  {
    path: "/goals",
    element: (
      <Layout>
        <GoalsInfoPage />
      </Layout>
    ),
  },
  {
    path: "/goals/:id",
    element: (
      <Layout>
        <GoalPage />
      </Layout>
    ),
  },
  {
    path: "/goals/:id/editGoal",
    element: (
      <Layout>
        <EditGoalFormPage />
      </Layout>
    ),
  },
  {
    path: "/addGoal",
    element: (
      <Layout>
        <AddGoalChoosePage />
      </Layout>
    ),
  },
  {
    path: "/addGoal/:type",
    element: (
      <Layout>
        <AddGoalFormPage />
      </Layout>
    ),
  },
];

const operationsRouter = [
  {
    path: "/operations",
    element: (
      <Layout>
        <OperationsInfoPage />
      </Layout>
    ),
  },
  {
    path: "/operations/:periodic/:kind/:id",
    element: (
      <Layout>
        <OperationPage />
      </Layout>
    ),
  },
  {
    path: "/addOperation",
    element: (
      <Layout>
        <AddOperationChooseIsPeriodic />
      </Layout>
    ),
  },
  {
    path: "/addOperation/:periodic",
    element: (
      <Layout>
        <AddOperationChooseKindPage />
      </Layout>
    ),
  },
  {
    path: "/addOperation/:periodic/:kind",
    element: (
      <Layout>
        <AddOperationChooseTypePage />
      </Layout>
    ),
  },
  {
    path: "/addOperation/:periodic/:kind/:type",
    element: (
      <Layout>
        <AddOperationFormPage />
      </Layout>
    ),
  },
];

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <Layout>
        <DashboardPage />
      </Layout>
    ),
  },
  {
    path: "/intro",
    element: <IntroPage />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/registration",
    element: <RegistrationPage />,
  },
  {
    path: "/info",
    element: (
      <Layout>
        <InfoPage />
      </Layout>
    ),
  },
  {
    path: "/profile",
    element: (
      <Layout>
        <ProfilePage />
      </Layout>
    ),
  },
  ...goalsRouter,
  ...operationsRouter,
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
